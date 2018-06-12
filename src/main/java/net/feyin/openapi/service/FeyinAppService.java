package net.feyin.openapi.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import net.feyin.openapi.exception.ResponseException;
import net.feyin.openapi.model.FeyinAuthMember;
import net.feyin.openapi.model.FeyinAuthMemberInfo;
import net.feyin.openapi.model.auth.Token;
import net.feyin.openapi.util.FeyinRequestErrorHandel;
import net.feyin.openapi.util.HttpClientUtil;
import net.feyin.openapi.util.JsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;


public class FeyinAppService {

    private Token token;

    public FeyinAppService(Token token) {
        this.token = token;
    }

    /**
     * 查询指定授权商户的信息
     * @param memberCode
     * @return
     */
    public FeyinAuthMemberInfo queryAuthMemberInfo(String memberCode) {
        Preconditions.checkArgument(token != null && StringUtils.isNotBlank(token.getAccess_token()), "token can't be null");
        String url = String.format("https://api.open.feyin.net/member/%s?access_token=%s", memberCode, token.getAccess_token());
        String resp = HttpClientUtil.sendGetRequest(url);
        FeyinAuthMemberInfo info = null;
        try {
            info = JsonUtil.parse(resp, FeyinAuthMemberInfo.class);
        } catch (ResponseException e) {
            FeyinRequestErrorHandel.errorHandel(resp);
        }
        return info;
    }

    /**
     * 查询所有授权的用户
     * @param appId
     * @return
     */
    public List<FeyinAuthMember> queryAllAuthMember(String appId) {
        Preconditions.checkArgument(token != null && StringUtils.isNotBlank(token.getAccess_token()), "token can't be null");
        String url = String.format("https://api.open.feyin.net/app/%s/members?access_token=%s", appId, token.getAccess_token());
        String resp = HttpClientUtil.sendGetRequest(url);
        List<FeyinAuthMember> list = Lists.newArrayList();
        try {
            list = JsonUtil.parseArray(resp, FeyinAuthMember.class);
        } catch (ResponseException e) {
            FeyinRequestErrorHandel.errorHandel(resp);
        }
        return list;
    }
}
