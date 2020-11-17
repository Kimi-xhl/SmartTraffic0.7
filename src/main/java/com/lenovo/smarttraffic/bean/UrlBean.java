package com.lenovo.smarttraffic.bean;

public class UrlBean {
    private String url;
    private String port;

    public UrlBean(String url, String port) {
        this.url = url;
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
