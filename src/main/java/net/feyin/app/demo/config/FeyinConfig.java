package net.feyin.app.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FeyinConfig {

    @Value("${feyin.account.member.code}")
    private String memberCode;

    @Value("${feyin.account.app.key}")
    private String appKey;

    @Value("${feyin.account.app.id}")
    private String appId;

    public String getAppId() {
        return appId;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public String getAppKey() {
        return appKey;
    }
}
