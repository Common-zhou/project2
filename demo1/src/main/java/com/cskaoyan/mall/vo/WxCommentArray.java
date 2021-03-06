package com.cskaoyan.mall.vo;

import java.util.Arrays;
import java.util.Date;

public class WxCommentArray {
    private Integer id;
    private Integer valueId;
    private Integer type;
    private String content;
    private Integer userId;
    private Boolean hasPicture;
    private String[] picUrls;
    private Short star;
    private Date addTime;
    private Date updateTime;
    private Boolean deleted;

    @Override
    public String toString() {
        return "WxCommentArray{" +
                "id=" + id +
                ", valueId=" + valueId +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", hasPicture=" + hasPicture +
                ", picUrls=" + Arrays.toString(picUrls) +
                ", star=" + star +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getHasPicture() {
        return hasPicture;
    }

    public void setHasPicture(Boolean hasPicture) {
        this.hasPicture = hasPicture;
    }

    public String[] getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(String[] picUrls) {
        this.picUrls = picUrls;
    }

    public Short getStar() {
        return star;
    }

    public void setStar(Short star) {
        this.star = star;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
