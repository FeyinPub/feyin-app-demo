package net.feyin.openapi.model;

public class MsgPrintingStatusResponse {
    private String msg_no;
    private String status;
    private String msg_time;
    private String print_time;

    public String getMsg_no() {
        return msg_no;
}

    public void setMsg_no(String msg_no) {
        this.msg_no = msg_no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg_time() {
        return msg_time;
    }

    public void setMsg_time(String msg_time) {
        this.msg_time = msg_time;
    }

    public String getPrint_time() {
        return print_time;
    }

    public void setPrint_time(String print_time) {
        this.print_time = print_time;
    }

    @Override
    public String toString() {
        return "MsgPrintingStatusResponse{" +
                "msg_no='" + msg_no + '\'' +
                ", status='" + status + '\'' +
                ", msg_time=" + msg_time +
                ", print_time=" + print_time +
                '}';
    }
}
