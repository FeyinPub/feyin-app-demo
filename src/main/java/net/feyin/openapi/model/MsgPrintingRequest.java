package net.feyin.openapi.model;

public class MsgPrintingRequest {
    private String device_no;
    private String msg_no;
    private String appid;
    private String msg_content;
    private String template_id;
    private String template_data;

    public String getDevice_no() {
        return device_no;
    }

    public void setDevice_no(String device_no) {
        this.device_no = device_no;
    }

    public String getMsg_no() {
        return msg_no;
    }

    public void setMsg_no(String msg_no) {
        this.msg_no = msg_no;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getTemplate_data() {
        return template_data;
    }

    public void setTemplate_data(String template_data) {
        this.template_data = template_data;
    }

    @Override
    public String toString() {
        return "MsgPrintingRequest{" +
                "device_no='" + device_no + '\'' +
                ", msg_no='" + msg_no + '\'' +
                ", appid='" + appid + '\'' +
                ", msg_content='" + msg_content + '\'' +
                ", template_id='" + template_id + '\'' +
                ", template_data='" + template_data + '\'' +
                '}';
    }
}
