package net.feyin.app.demo.controller;

import net.feyin.app.demo.config.FeyinConfig;
import net.feyin.app.demo.repository.FeyinAccountManager;
import net.feyin.app.demo.service.FeyinApiService;
import net.feyin.app.demo.util.DateTimeCheckUtil;
import net.feyin.app.demo.util.FeyinResultMapUtil;
import net.feyin.app.demo.util.MD5Util;
import net.feyin.openapi.model.MsgPrintingRequest;
import net.feyin.openapi.service.FeyinPrintService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/test/feyin")
public class FeyinIndexController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(FeyinIndexController.class);

    @Autowired
    private FeyinConfig feyinConfig;
    @Autowired
    private FeyinApiService feyinApiService;

    /**
     * 飞印应用用户登录的首页，需要检测访问合法性
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(String uid, String time, String secret) {
        String accountId = getFeyinAccount();
        if (accountId == null) {
            long times = Long.parseLong(time);
            if (DateTimeCheckUtil.isExpireOneMinute(times)) {
                log.info("feyin member {} login timeout", uid);
                return "no_login";
            }
            String appKey = feyinConfig.getAppKey();
            String s = MD5Util.getMD5String(uid + time + appKey);
            if (!StringUtils.equals(secret, s)) {
                log.info("feyin member {} login sign error", uid);
                return "no_login";
            }
            if (!FeyinAccountManager.checkAccount(uid)) {
                feyinApiService.authAddFeyinMember(uid);
            }
            session.setAttribute(FEYIN_ACCOUNT, uid);
            log.info("user {} login success", uid);
        }
        return "index";
    }

    /**
     *  获取用户的所有打印机
     */
    @ResponseBody
    @RequestMapping(value = "/getMemberPrinter", method = RequestMethod.POST)
    public Map getMemberPrinter() {
        String accountId = getFeyinAccount();
        if (accountId == null) {
            return FeyinResultMapUtil.getErrorMap("-1", "请先通过飞印公众号登录");
        }
        List<String> feyinDevices = FeyinAccountManager.getAccountDevices(accountId);
        return FeyinResultMapUtil.getSuccessDataMap(feyinDevices);
    }

    /**
     *  打印功能测试
     */
    @ResponseBody
    @RequestMapping(value = "/printTest", method = RequestMethod.POST)
    public Map printTest(String no, String msg) {
        String accountId = getFeyinAccount();
        if (accountId == null) {
            return FeyinResultMapUtil.getErrorMap("-1", "请先通过飞印公众号登录");
        }
        List<String> feyinDevices = FeyinAccountManager.getAccountDevices(accountId);
        if (!feyinDevices.contains(no)) {
            return FeyinResultMapUtil.getErrorMap("404", "无法找到打印机");
        }
        FeyinPrintService feyinPrintService = new FeyinPrintService(feyinApiService.getToken());
        MsgPrintingRequest msgPrintingRequest = new MsgPrintingRequest();
        msgPrintingRequest.setDevice_no(no);
        msgPrintingRequest.setAppid(feyinConfig.getAppId());
        msgPrintingRequest.setMsg_content(msg);
        String msgNo = feyinPrintService.sendMsg(msgPrintingRequest);
        log.info("no {} send msg success, msg no: {}", no, msgNo);
        return FeyinResultMapUtil.getSuccessMap();
    }

}
