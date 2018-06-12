package net.feyin.openapi.model;

public class FeyinClearOrderResponse {
    private String clear_cnt;

    public String getClear_cnt() {
        return clear_cnt;
    }

    public void setClear_cnt(String clear_cnt) {
        this.clear_cnt = clear_cnt;
    }

    @Override
    public String toString() {
        return "FeyinClearOrderResponse{" +
                "clear_cnt='" + clear_cnt + '\'' +
                '}';
    }
}
