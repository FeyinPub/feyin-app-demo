package net.feyin.app.demo.service;

import com.google.common.collect.Lists;
import net.feyin.app.demo.config.FeyinConfig;
import net.feyin.app.demo.repository.FeyinAccountManager;
import net.feyin.openapi.manager.FeyinTokenManager;
import net.feyin.openapi.model.FeyinAuthMemberDeviceInfo;
import net.feyin.openapi.model.FeyinAuthMemberInfo;
import net.feyin.openapi.model.auth.Token;
import net.feyin.openapi.service.FeyinAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeyinApiService {

    private static Logger log = LoggerFactory.getLogger(FeyinApiService.class);

    @Autowired
    private FeyinConfig feyinConfig;

    public Token getToken() {
        return FeyinTokenManager.getToken(feyinConfig.getMemberCode(), feyinConfig.getAppKey());
    }

    //用户授权处理
    public void authAddFeyinMember(String uid) {
        FeyinAccountManager.addAccount(uid);
        log.info("member {} auth add success", uid);

        updateMemberDevice(uid);
    }

    //更新用户设备列表
    public void updateMemberDevice(String uid) {
        FeyinAppService feyinAppService = new FeyinAppService(getToken());
        FeyinAuthMemberInfo info = feyinAppService.queryAuthMemberInfo(uid);
        List<String> devices = Lists.newArrayList();
        for (FeyinAuthMemberDeviceInfo deviceInfo : info.getDevices()) {
            devices.add(deviceInfo.getDevice_no());
        }
        FeyinAccountManager.addDevice(uid, devices);
    }

    //用户取消授权
    public void authRemoveFeyinMember(String uid) {
        if (!FeyinAccountManager.checkAccount(uid)) {
            log.info("feyin member not fount");
            return;
        }
        FeyinAccountManager.removeAccount(uid);
        log.info("member {} remove auth success", uid);
    }
}
