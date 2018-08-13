package net.feyin.openapi.model;

import java.util.List;

public class FeyinAuthMemberInfo {
    private String uid;
    private String name;
    private List<FeyinAuthMemberDeviceInfo> devices;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FeyinAuthMemberDeviceInfo> getDevices() {
        return devices;
    }

    public void setDevices(List<FeyinAuthMemberDeviceInfo> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "FeyinAuthMemberInfo{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", devices=" + devices +
                '}';
    }
}