package net.feyin.openapi.model;

public class FeyinLabelTemplate {
    private String name;
    private String content;
    private String catalog;
    private String desc;
    private String updated_at;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "FeyinLabelTemplate{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", catalog='" + catalog + '\'' +
                ", desc='" + desc + '\'' +
                ", updated_at=" + updated_at +
                '}';
    }
}
