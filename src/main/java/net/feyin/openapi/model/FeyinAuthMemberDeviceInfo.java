package net.feyin.openapi.model;

public class FeyinAuthMemberDeviceInfo {
    private String device_no;
    private String model;
    private String memo;

    public String getDevice_no() {
        return device_no;
    }

    public void setDevice_no(String device_no) {
        this.device_no = device_no;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "FeyinAuthMemberDeviceInfo{" +
                "device_no='" + device_no + '\'' +
                ", model='" + model + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}