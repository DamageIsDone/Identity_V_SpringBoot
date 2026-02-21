package com.example.mysite.controller;

import com.example.mysite.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private WechatService wechatService;

    private Map<String, Object> success(Object data) {
        Map<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("message", "成功");
        res.put("data", data);
        return res;
    }

    private Map<String, Object> error(String message) {
        Map<String, Object> res = new HashMap<>();
        res.put("code", 400);
        res.put("message", message);
        return res;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> request) {
        try {
            String code = request.get("code");
            Map<String, Object> result = wechatService.login(code);
            return success(result);
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    @PostMapping("/bind")
    public Map<String, Object> bind(@RequestBody Map<String, String> request) {
        try {
            String openid = request.get("openid");
            String username = request.get("username");
            String password = request.get("password");
            wechatService.bind(openid, username, password);
            return success(null);
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    @PostMapping("/unbind")
    public Map<String, Object> unbind(@RequestBody Map<String, String> request) {
        try {
            String openid = request.get("openid");
            wechatService.unbind(openid);
            return success(null);
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> request) {
        try {
            String openid = request.get("openid");
            String username = request.get("username");
            String password = request.get("password");
            String nickname = request.get("nickname");
            Map<String, Object> userInfo = wechatService.registerAndBind(openid, username, password, nickname);
            return success(userInfo);
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }
}