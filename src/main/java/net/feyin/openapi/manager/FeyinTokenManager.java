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

}
