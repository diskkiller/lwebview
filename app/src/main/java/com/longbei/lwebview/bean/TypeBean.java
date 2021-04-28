package com.longbei.lwebview.bean;

import com.luck.picture.lib.entity.LocalMedia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TypeBean implements Serializable {

    private String id;

    private String title;

    private String parentId;

    private String content;

    private List<LocalMedia> data;

    public List<LocalMedia> getData() {
        return data;
    }

    public void setData(List<LocalMedia> data) {
        this.data = data;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private List<TypeBean> list = new ArrayList();

    public List<TypeBean> getList() {
        return list;
    }

    public void setList(List<TypeBean> list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
