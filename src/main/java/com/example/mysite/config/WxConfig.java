package com.example.mysite.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wx")
public class WxConfig {
    private String appid;
    private String secret;
    private String jscode2sessionUrl;

    // getters and setters
    public String getAppid() { return appid; }
    public void setAppid(String appid) { this.appid = appid; }
    public String getSecret() { return secret; }
    public void setSecret(String secret) { this.secret = secret; }
    public String getJscode2sessionUrl() { return jscode2sessionUrl; }
    public void setJscode2sessionUrl(String jscode2sessionUrl) { this.jscode2sessionUrl = jscode2sessionUrl; }
}