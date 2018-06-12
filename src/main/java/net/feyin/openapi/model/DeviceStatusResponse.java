package net.feyin.openapi.model;

import java.util.Date;

public class DeviceStatusResponse {
    private String device_no;
    private String since;
    private String status;
    private String paper_status;
    private Date last_connected_at;

    public String getDevice_no() {
        return device_no;
    }

    public void setDevice_no(String device_no) {
        this.device_no = device_no;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaper_status() {
        return paper_status;
    }

    public void setPaper_status(String paper_status) {
        this.paper_status = paper_status;
    }

    public Date getLast_connected_at() {
        return last_connected_at;
    }

    public void setLast_connected_at(Date last_connected_at) {
        this.last_connected_at = last_connected_at;
    }

    @Override
    public String toString() {
        return "DeviceStatusResponse{" +
                "device_no='" + device_no + '\'' +
                ", since='" + since + '\'' +
                ", status='" + status + '\'' +
                ", paper_status='" + paper_status + '\'' +
                ", last_connected_at=" + last_connected_at +
                '}';
    }
}
