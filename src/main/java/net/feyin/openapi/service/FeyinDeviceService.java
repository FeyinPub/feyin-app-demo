package net.feyin.openapi.service;

import com.google.common.base.Preconditions;
import net.feyin.openapi.model.DeviceStatusResponse;
import net.feyin.openapi.model.auth.Token;
import net.feyin.openapi.util.FeyinRequestErrorHandel;
import net.feyin.openapi.util.HttpClientUtil;
import net.feyin.openapi.util.JsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class FeyinDeviceService {

    private Token token;

    public FeyinDeviceService(Token token) {
        this.token = token;
    }

    /**
     * 查询名下所有打印机的状态
     * @return
     */
    public List<DeviceStatusResponse> queryAllDeviceStatus() {
        Preconditions.checkArgument(token != null && StringUtils.isNotBlank(token.getAccess_token()), "token can't be null");
        String url = String.format("https://api.open.feyin.net/devices?access_token=%s", token.getAccess_token());
        String resp = HttpClientUtil.sendGetRequest(url);
        if (StringUtils.isBlank(resp) || !resp.contains("device_no")) {
            FeyinRequestErrorHandel.errorHandel(resp);
        }
        return JsonUtil.parseArray(resp, DeviceStatusResponse.class);
    }
}
