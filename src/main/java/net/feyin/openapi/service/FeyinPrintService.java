package net.feyin.openapi.service;

import com.google.common.base.Preconditions;
import net.feyin.openapi.model.ErrorResponse;
import net.feyin.openapi.model.MsgPrintingRequest;
import net.feyin.openapi.model.MsgPrintingStatusResponse;
import net.feyin.openapi.model.auth.Token;
import net.feyin.openapi.util.FeyinRequestErrorHandel;
import net.feyin.openapi.util.HttpClientUtil;
import net.feyin.openapi.util.JsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.Map;


public class FeyinPrintService {

    private Token token;

    public FeyinPrintService(Token token) {
        this.token = token;
    }

    /**
     * 发送打印
     * @return 消息id
     */
    public String sendMsg(MsgPrintingRequest request) {
        Preconditions.checkArgument(token != null && StringUtils.isNotBlank(token.getAccess_token()), "token can't be null");
        Preconditions.checkArgument(StringUtils.isNotBlank(request.getDevice_no()), "device no can't be null");
        String url = String.format("https://api.open.feyin.net/msg?access_token=%s", token.getAccess_token());
        String param = JsonUtil.toJson(request);
        String resp = HttpClientUtil.sendJsonRequest(url, param);
        if (StringUtils.isBlank(resp) || !resp.contains("msg_no")) {
            FeyinRequestErrorHandel.errorHandel(resp);
        }
        Map result = JsonUtil.parse(resp, Map.class);
        return result.get("msg_no").toString();
    }

    /**
     *  检查指定消息打印状态
     * @param msgNo 消息id
     * @return
     */
    public MsgPrintingStatusResponse queryMsgStatus(String msgNo) {
        Preconditions.checkArgument(token != null && StringUtils.isNotBlank(token.getAccess_token()), "token can't be null");
        Preconditions.checkArgument(StringUtils.isNotBlank(msgNo), "msg no can't be null");
        String url = String.format("https://api.open.feyin.net/msg/%s/status?access_token=%s", msgNo, token.getAccess_token());
        String resp = HttpClientUtil.sendGetRequest(url);
        if (StringUtils.isBlank(resp) || !resp.contains("msg_no")) {
            FeyinRequestErrorHandel.errorHandel(resp);
        }
        return JsonUtil.parse(resp, MsgPrintingStatusResponse.class);
    }

    /**
     * 撤销指定消息的打印
     * @param msgNo
     * @return
     */
    public ErrorResponse cancelMsg(String msgNo) {
        Preconditions.checkArgument(token != null && StringUtils.isNotBlank(token.getAccess_token()), "token can't be null");
        Preconditions.checkArgument(StringUtils.isNotBlank(msgNo), "msg no can't be null");
        String url = String.format("https://api.open.feyin.net/msg/%s/cancel?access_token=%s", msgNo, token.getAccess_token());
        String resp = HttpClientUtil.sendPostRequest(url, null);
        return FeyinRequestErrorHandel.checkResponseError(resp);
    }

    /**
     * 撤销所有未打印的信息
     * @param deviceNo
     * @return
     */
    public ErrorResponse cancelAllMsg(String deviceNo) {
        Preconditions.checkArgument(token != null && StringUtils.isNotBlank(token.getAccess_token()), "token can't be null");
        Preconditions.checkArgument(StringUtils.isNotBlank(deviceNo), "device no can't be null");
        String url = String.format("https://api.open.feyin.net/device/%s/msg/clear?access_token=%s", deviceNo, token.getAccess_token());
        String resp = HttpClientUtil.sendPostRequest(url, null);
        return FeyinRequestErrorHandel.checkResponseError(resp);
    }
}
