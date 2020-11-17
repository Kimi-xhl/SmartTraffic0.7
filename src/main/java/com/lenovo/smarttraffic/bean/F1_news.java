package com.lenovo.smarttraffic.bean;

import java.io.Serializable;

/**
 * author: LBX
 * date:   On 2019/5/30
 */
public class F1_news implements Serializable {
    private String title;
    private String content;
    private String imgurl;
    private String time;
    private String category;
    private long longtime;


    public F1_news(String title, String content, String imgurl, String time, String category, long longtime) {
        this.title = title;
        this.content = content;
        this.imgurl = imgurl;
        this.time = time;
        this.category = category;
        this.longtime = longtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getLongtime() {
        return longtime;
    }

    public void setLongtime(long longtime) {
        this.longtime = longtime;
    }
}
