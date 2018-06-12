package net.feyin.openapi.model;

import java.util.Map;

public class FeyinCallback {
    private String msg_type;
    private Map<String, Object> payload;

    public String getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(String msg_type) {
        this.msg_type = msg_type;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "FeyinCallback{" +
                "msg_type='" + msg_type + '\'' +
                ", payload=" + payload +
                '}';
    }
}
