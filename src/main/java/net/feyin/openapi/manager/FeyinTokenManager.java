package net.feyin.openapi.manager;


import com.google.common.base.Preconditions;
import net.feyin.openapi.model.auth.FeyinToken;
import net.feyin.openapi.model.auth.Token;
import net.feyin.openapi.util.FeyinRequestErrorHandel;
import net.feyin.openapi.util.HttpClientUtil;
import net.feyin.openapi.util.JsonUtil;
import org.apache.commons.lang.StringUtils;

public class FeyinTokenManager {

    private static final FeyinToken FEYIN_TOKEN = new FeyinToken();
    private static final FeyinToken FEYIN_APP_TOKEN = new FeyinToken();

    /**
     * 通用的token获取接口。
     * 开发第三方应用，建议使用应用级别的token，防止多应用干扰
     */
    public static Token getToken(String memberCode, String secret) {
        if (FEYIN_TOKEN.getAccess_token() == null || FEYIN_TOKEN.isExpires()) {
            Preconditions.checkArgument(StringUtils.isNotBlank(memberCode), "memberCode can't be null");
            Preconditions.checkArgument(StringUtils.isNotBlank(secret), "secret can't be null");

            String url = String.format("https://api.open.feyin.net/token?code=%s&secret=%s", memberCode, secret);
            String resp = HttpClientUtil.sendGetRequest(url);
            if (StringUtils.isBlank(resp)) {
                throw new RuntimeException("refresh token error!");
            }
            if (!resp.contains("access_token")) {
                FeyinRequestErrorHandel.errorHandel(resp);
            }
            Token token = JsonUtil.parse(resp, Token.class);
            FEYIN_TOKEN.setToken(token);
            System.out.println("refresh token success");
            return token;
        }
        return FEYIN_TOKEN.getToken();
    }

    /**
     * 应用专用的token获取接口
     */
    public static Token getTokenByApp(String memberCode, String secret, String appId) {
        if (FEYIN_APP_TOKEN.getAccess_token() == null || FEYIN_APP_TOKEN.isExpires()) {
            Preconditions.checkArgument(StringUtils.isNotBlank(memberCode), "memberCode can't be null");
            Preconditions.checkArgument(StringUtils.isNotBlank(secret), "secret can't be null");
            Preconditions.checkArgument(StringUtils.isNotBlank(appId), "appId can't be null");
            String url = String.format("https://api.open.feyin.net/token?code=%s&secret=%s&appid=%s", memberCode, secret, appId);
            String resp = HttpClientUtil.sendGetRequest(url);
            if (StringUtils.isBlank(resp)) {
                throw new RuntimeException("refresh token error!");
            }
            if (!resp.contains("access_token")) {
                FeyinRequestErrorHandel.errorHandel(resp);
            }
            Token token = JsonUtil.parse(resp, Token.class);
            FEYIN_APP_TOKEN.setToken(token);
            return token;
        }
        return FEYIN_APP_TOKEN.getToken();
    }
}
