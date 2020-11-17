package com.lenovo.smarttraffic.api;

import com.lenovo.smarttraffic.util.Util;

public class UrlUtils {
    public static String getImageUrl;
    public static String getUrl() {
        String url = Util.getSetting().getUrl();
        String port = Util.getSetting().getPort();
        getImageUrl="http://" + url + ":" + port + "/transportservice";
        return "http://" + url + ":" + port + "/transportservice/action/";
    }
    public static String url212 = getUrl() + "GetCarAccountBalance.do";
    public static String url213 = getUrl() + "SetCarAccountRecharge.do";
    public static String url241 = getUrl() + "GetAllSense.do";
    public static String url331 = getUrl() + "GetSUserInfo.do";
    public static String url321 = getUrl() + "GetCarInfo.do";
    public static String url2111 = getUrl() + "GetMetroInfo.do";
    public static String url215 = getUrl() + "GetAllCarPeccancy.do";
    public static String url361 = getUrl() + "user_login.do";
    public static String url281 = getUrl() + "GetPeccancyType.do";
    public static String url271 = getUrl() + "GetRoadStatus.do";
    public static String url2171 = getUrl() + "GetNewsInfo.do";
    public static String url2161 = getUrl() + "GetSpotInfo.do";
}
