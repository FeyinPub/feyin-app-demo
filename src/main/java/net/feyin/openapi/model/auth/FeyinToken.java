package net.feyin.openapi.model.auth;

import net.feyin.openapi.util.DateUtils;

import java.util.Date;

public class FeyinToken extends Token {
    private Date expiresTime;

    public Date getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(Date expiresTime) {
        this.expiresTime = expiresTime;
    }

    public FeyinToken() {
    }

    public FeyinToken(Token token) {
        this.setAccess_token(token.getAccess_token());
        this.setExpires_in(token.getExpires_in());
        //设置提前5分钟为过期
        this.expiresTime = DateUtils.secondOffset(new Date(), token.getExpires_in() - 60*5);
    }

    public void setToken(Token token) {
        this.setAccess_token(token.getAccess_token());
        this.setExpires_in(token.getExpires_in());
        this.expiresTime = DateUtils.secondOffset(new Date(), token.getExpires_in() - 60*5);
    }

    public Token getToken() {
        return new Token(this.getAccess_token(), this.getExpires_in());
    }

    /**
     * token是否已过期, 已过期返回true
     */
    public boolean isExpires() {
        return this.expiresTime.before(new Date());
    }
}
