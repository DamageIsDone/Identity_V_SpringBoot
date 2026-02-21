package com.example.mysite.service;

import com.example.mysite.util.WxOpenIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class WechatService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private WxOpenIdUtil wxOpenIdUtil;

    public Map<String, Object> login(String code) {
        String openid = wxOpenIdUtil.getOpenId(code);
        System.out.println("login 方法中得到的 openid: " + openid);
        if (openid == null) {
            throw new RuntimeException("获取openid失败");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("openid", openid);

        String sql = "SELECT game_user_id FROM wechat_binding WHERE openid = ?";
        try {
            // 显式指定参数类型
            Integer gameUserId = jdbcTemplate.queryForObject(sql,
                    new Object[]{openid},
                    new int[]{java.sql.Types.VARCHAR},
                    Integer.class);

            // 如果找到绑定记录，查询用户信息
            String userSql = "SELECT user_id, username FROM users WHERE user_id = ?";
            Map<String, Object> user = jdbcTemplate.queryForMap(userSql, gameUserId);
            result.put("bound", true);
            result.put("user", user);
        } catch (EmptyResultDataAccessException e) {
            // 表是空的，正常情况
            System.out.println("未找到绑定记录，bound 设为 false");
            result.put("bound", false);
            result.put("user", null);
        } catch (Exception e) {
            // 打印完整堆栈，方便定位问题
            System.err.println("数据库查询发生异常：");
            e.printStackTrace(); // 关键：打印堆栈
            throw new RuntimeException("数据库查询失败: " + e.getMessage(), e);
        }
        return result;
    }
    @Transactional
    public void bind(String openid, String username, String password) {
        String verifySql = "SELECT user_id FROM users WHERE phone = ? AND password = ?";
        Integer userId;
        try {
            userId = jdbcTemplate.queryForObject(verifySql, Integer.class, username, password);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("用户名或密码错误");
        }

        String checkBoundSql = "SELECT openid FROM wechat_binding WHERE game_user_id = ?";
        try {
            String existingOpenid = jdbcTemplate.queryForObject(checkBoundSql, String.class, userId);
            if (existingOpenid != null) {
                throw new RuntimeException("该游戏账号已被绑定");
            }
        } catch (EmptyResultDataAccessException ignored) {}

        String checkOpenidSql = "SELECT game_user_id FROM wechat_binding WHERE openid = ?";
        try {
            Integer existingUserId = jdbcTemplate.queryForObject(checkOpenidSql, Integer.class, openid);
            if (existingUserId != null) {
                throw new RuntimeException("该微信已绑定其他账号");
            }
        } catch (EmptyResultDataAccessException ignored) {}

        String insertSql = "INSERT INTO wechat_binding (openid, game_user_id) VALUES (?, ?)";
        jdbcTemplate.update(insertSql, openid, userId);
    }

    @Transactional
    public void unbind(String openid) {
        String sql = "DELETE FROM wechat_binding WHERE openid = ?";
        int rows = jdbcTemplate.update(sql, openid);
        if (rows == 0) {
            throw new RuntimeException("未找到绑定关系");
        }
    }

    @Transactional
    public Map<String, Object> registerAndBind(String openid, String username, String password, String nickname) {
        String checkUserSql = "SELECT user_id FROM users WHERE phone = ?";
        try {
            jdbcTemplate.queryForObject(checkUserSql, Integer.class, username);
            throw new RuntimeException("用户名已存在");
        } catch (EmptyResultDataAccessException ignored) {}

        String checkOpenidSql = "SELECT game_user_id FROM wechat_binding WHERE openid = ?";
        try {
            jdbcTemplate.queryForObject(checkOpenidSql, Integer.class, openid);
            throw new RuntimeException("该微信已绑定账号");
        } catch (EmptyResultDataAccessException ignored) {}

        String defaultQuestion = "默认密保问题";
        String defaultAnswer = "默认答案";
        String insertUserSql = "INSERT INTO users (username, password, phone, security_question, security_answer, is_manager) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertUserSql, nickname != null ? nickname : username, password, username, defaultQuestion, defaultAnswer, false);

        Integer newUserId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        String insertBindSql = "INSERT INTO wechat_binding (openid, game_user_id) VALUES (?, ?)";
        jdbcTemplate.update(insertBindSql, openid, newUserId);

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user_id", newUserId);
        userInfo.put("username", username);
        userInfo.put("nickname", nickname != null ? nickname : username);
        return userInfo;
    }
}