package net.feyin.openapi.model;

public class ErrorResponse {
    private Integer errcode;
    private String errmsg;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public ErrorResponse(Integer errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }

    public static net.feyin.openapi.model.ErrorResponse getSuccessResponse() {
        return new net.feyin.openapi.model.ErrorResponse(0, "ok");
    }
}
