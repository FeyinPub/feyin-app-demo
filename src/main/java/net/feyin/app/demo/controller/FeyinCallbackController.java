package net.feyin.app.demo.controller;

import net.feyin.app.demo.config.FeyinConfig;
import net.feyin.app.demo.service.FeyinApiService;
import net.feyin.app.demo.util.DateTimeCheckUtil;
import net.feyin.app.demo.util.MD5Util;
import net.feyin.openapi.util.DateUtils;
import net.feyin.openapi.enumeration.FeyinCallbackType;
import net.feyin.openapi.model.ErrorResponse;
import net.feyin.openapi.model.FeyinCallback;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping(value = "/test/feyin")
public class FeyinCallbackController {

    private static Logger log = LoggerFactory.getLogger(FeyinCallbackController.class);

    @Autowired
    private FeyinApiService feyinApiService;
    @Autowired
    private FeyinConfig feyinConfig;

    /**
     * 飞印回调接口，需要检测回调的合法性
     */
    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    public ErrorResponse callback(@RequestBody FeyinCallback feyinCallback) {
        FeyinCallbackType type = FeyinCallbackType.check(feyinCallback.getMsg_type());
        if (type != null) {
            Map<String, Object> payload = feyinCallback.getPayload();
            String appKey = feyinConfig.getAppKey();
            String s;
            switch (type) {
                case heartbeat:
                    //心跳检测
                    log.info("heartbeat");
                    break;
                case auth_added:
                    //用户授权应用
                    String uid = payload.get("uid").toString();
                    String appid = payload.get("appid").toString();
                    String time = payload.get("time").toString();
                    String secret = payload.get("secret").toString();
                    Date date = DateUtils.parseDatetime(time);
                    if (DateTimeCheckUtil.isExpireOneMinute(date)) {
                        log.info("time is expire");
                        break;
                    }
                    s = MD5Util.getMD5String(appid + time + uid + appKey);
                    if (StringUtils.equals(s, secret)) {
                        feyinApiService.authAddFeyinMember(uid);
                    } else {
                        log.info("secret is {}, check secret is {}, sign error", secret, s);
                    }
                    break;
                case auth_removed:
                    //用户取消授权
                    uid = payload.get("uid").toString();
                    appid = payload.get("appid").toString();
                    time = payload.get("time").toString();
                    secret = payload.get("secret").toString();
                    date = DateUtils.parseDatetime(time);
                    if (DateTimeCheckUtil.isExpireOneMinute(date)) {
                        log.info("time is expire");
                        break;
                    }
                    s = MD5Util.getMD5String(appid + time + uid + appKey);
                    if (StringUtils.equals(s, secret)) {
                        feyinApiService.authRemoveFeyinMember(uid);
                    } else {
                        log.info("secret is {}, check secret is {}, sign error", secret, s);
                    }
                    break;
                case msg_printed:
                    //打印状态回调，根据打印回调的消息id，来检测打印状态
                    String deviceNo = payload.get("device_no").toString();
                    String printTime = payload.get("print_time").toString();
                    String msgNo = payload.get("msg_no").toString();
                    secret = payload.get("secret").toString();
                    date = DateUtils.parseDatetime(printTime);
                    if (DateTimeCheckUtil.isExpireOneMinute(date)) {
                        log.info("time is expire");
                        break;
                    }
                    s = MD5Util.getMD5String(deviceNo + msgNo + printTime + appKey);
                    if (StringUtils.equals(s, secret)) {

                    } else {
                        log.info("secret is {}, check secret is {}, sign error", secret, s);
                    }
                    break;
                case device_status:
                    //设备状态回调，可自行根据设备状态进行相应的处理
                    deviceNo = payload.get("device_no").toString();
                    String onlineStatus = payload.get("online_status").toString();
                    String paperStatus = payload.get("paper_status").toString();
                    secret = payload.get("secret").toString();
                    s = MD5Util.getMD5String(deviceNo + onlineStatus + paperStatus + appKey);
                    if (StringUtils.equals(s, secret)) {

                    } else {
                        log.info("secret is {}, check secret is {}, sign error", secret, s);
                    }
                    break;
            }
        }
        return ErrorResponse.getSuccessResponse();
    }
}
