package net.feyin.app.demo.controller;

import net.feyin.app.demo.config.FeyinConfig;
import net.feyin.openapi.manager.FeyinTokenManager;
import net.feyin.openapi.model.auth.Token;
import net.feyin.openapi.util.CusAccessObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/feyin")
public class FeyinTokenController extends BaseController {

    private static final String[] ALLOW_IP = {};

    @Autowired
    private FeyinConfig feyinConfig;

    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    public Token getToken() {
        String ip = CusAccessObjectUtil.getIpAddress(request);
        for (String allowIp : ALLOW_IP) {
            if (allowIp.equals(ip)) return FeyinTokenManager.getToken(feyinConfig.getMemberCode(), feyinConfig.getAppKey());
        }
        return null;
    }
}
