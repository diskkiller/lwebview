package com.longbei.lwebview.bean;

public class TypeBean {

    private int id;

    private String title;

    private int parentId;

    private String parentTypeTitle;

    public int getType() {
        return id;
    }

    public void setType(int type) {
        this.id = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getParentType() {
        return parentId;
    }

    public void setParentType(int parentType) {
        this.parentId = parentType;
    }

}
