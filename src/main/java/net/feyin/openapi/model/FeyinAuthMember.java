package net.feyin.openapi.model;

public class FeyinAuthMember {
    private String uid;
    private String name;
    private String created_at;

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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "FeyinAuthMember{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
