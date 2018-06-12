package net.feyin.openapi.enumeration;

public enum FeyinErrorCode {
    SERVER_BUSY(-1, "系统繁忙，请稍后重试"),
    SUCCESS(0, "请求成功"),
    ILLEGAL_PARAM(40001, "不合法的请求参数"),
    ILLEGAL_DEVICE(40002, "不合法的设备编码或者设备未授权"),
    APP_NOT_FOUNT(40003, "应用id不存在"),
    MEMBER_ID_NOT_FOUNT(40004, "商户id不存在"),
    MSG_NO_NOT_FOUNT(40005, "msg_no不存在"),
    KEY_ERROR(40011, "密钥错误"),
    ILLEGAL_IP(40012, "ip不允许，请设置ip白名单"),
    ILLEGAL_TOKEN(40014, "不合法的access_token"),
    PRINTING_FAIL(40020, "消息打印失败"),
    BIND_FAIL(40030, "打印机绑定失败"),
    UNBIND_FAIL(40031, "打印解除机绑定失败"),
    APP_PERMISSION_DENIED(40080, "无权访问不属于自身的应用"),
    MEMBER_NOT_FOUNT(40081, "商户信息不可访问或不存在"),
    MEMBER_NO_AUTH(40082, "商户未授权");

    private Integer errcode;
    private String errmsg;

    FeyinErrorCode(Integer errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public static String getCodeMsg(int code) {
        for (net.feyin.openapi.enumeration.FeyinErrorCode errorCode : net.feyin.openapi.enumeration.FeyinErrorCode.values()) {
            if (errorCode.errcode == code) {
                return errorCode.errmsg;
            }
        }
        return "未知错误";
    }
}
