package net.feyin.app.demo.controller;

import net.feyin.app.demo.config.FeyinConfig;
import net.feyin.app.demo.service.FeyinApiService;
import net.feyin.app.demo.util.FeyinResultMapUtil;
import net.feyin.openapi.model.DeviceStatusResponse;
import net.feyin.openapi.model.MsgPrintingRequest;
import net.feyin.openapi.service.FeyinDeviceService;
import net.feyin.openapi.service.FeyinPrintService;
import net.feyin.openapi.util.DateUtils;
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


/**
 *  这个方法仅供测试使用，正式的飞印应用不需要保留，建议上线前删除
 */
@Controller
@RequestMapping(value = "/feyin/test")
public class FeyinTestController {

    private static Logger log = LoggerFactory.getLogger(FeyinIndexController.class);

    private static String defaultTestMsgFormat =
            "<center>飞印测试页\n" +
            "<left>测试时间：%s\n" +
            "终端编号：%s\n" +
            "<Barcode# Type=5 Height=10>%s</Barcode#>" +
            "飞印终端能在全球范围内进行网络信息传递并打印，不受地域与打印设备的限制，为您提供更大的便利！\n" +
            "客服微信公众号：feyinnet\n" +
            "<center><QRcode# Size=6>http://weixin.qq.com/r/1TqNlcnEVpQprY3292-H</QRcode#>\n" +
            "Email：support@feyin.net\n" +
            "*******************************\n" +
            "飞印 - 商业更智能\n" +
            "进入智能云打印的时代\n" +
            "*******************************\n\n";

    @Autowired
    private FeyinApiService feyinApiService;
    @Autowired
    private FeyinConfig feyinConfig;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "test";
    }

    @ResponseBody
    @RequestMapping(value = "/getPrintStatus", method = RequestMethod.POST)
    public Map getPrintStatus() {
        FeyinDeviceService feyinDeviceService = new FeyinDeviceService(feyinApiService.getToken());
        List<DeviceStatusResponse> deviceStatusResponses = feyinDeviceService.queryAllDeviceStatus();
        return FeyinResultMapUtil.getSuccessDataMap(deviceStatusResponses);
    }

    @ResponseBody
    @RequestMapping(value = "/testPrint", method = RequestMethod.POST)
    public Map testPrint(String no) {
        FeyinPrintService feyinPrintService = new FeyinPrintService(feyinApiService.getToken());
        MsgPrintingRequest msgPrintingRequest = new MsgPrintingRequest();
        msgPrintingRequest.setDevice_no(no);
        msgPrintingRequest.setAppid(feyinConfig.getAppId());
        msgPrintingRequest.setMsg_content(String.format(defaultTestMsgFormat, DateUtils.getCurrDateTimeStr(), no, no));
        String msgNo = feyinPrintService.sendMsg(msgPrintingRequest);
        log.info("no {} send msg success, msg no: {}", no, msgNo);
        if (StringUtils.isBlank(msgNo)) {
            return FeyinResultMapUtil.getErrorMap("-1", "发送打印失败");
        }
        return FeyinResultMapUtil.getSuccessMap();
    }


}
