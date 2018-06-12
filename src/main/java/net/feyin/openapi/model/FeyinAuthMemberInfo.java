package net.feyin.openapi.model;

import java.util.List;

public class FeyinAuthMemberInfo {
    private String id;
    private String name;
    private String mobile;
    private List<FeyinAuthMemberDeviceInfo> devices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", devices=" + devices +
                '}';
    }
}