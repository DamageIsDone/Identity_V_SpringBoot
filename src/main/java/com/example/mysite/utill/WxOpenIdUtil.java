package com.example.mysite.util;

import com.example.mysite.config.WxConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class WxOpenIdUtil {

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private RestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getOpenId(String code) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(wxConfig.getJscode2sessionUrl())
                    .queryParam("appid", wxConfig.getAppid())
                    .queryParam("secret", wxConfig.getSecret())
                    .queryParam("js_code", code)
                    .queryParam("grant_type", "authorization_code")
                    .build()
                    .toUriString();

            String response = restTemplate.getForObject(url, String.class);
            System.out.println("微信接口原始响应: " + response);  // 添加此行
            JsonNode jsonNode = objectMapper.readTree(response);

            if (jsonNode.has("errcode") && jsonNode.get("errcode").asInt() != 0) {
                System.err.println("微信接口错误: " + jsonNode.get("errmsg").asText());
                return null;
            }

            return jsonNode.has("openid") ? jsonNode.get("openid").asText() : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}