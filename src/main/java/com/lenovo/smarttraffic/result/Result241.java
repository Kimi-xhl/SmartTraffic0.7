package com.lenovo.smarttraffic.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * author: LBX
 * date:   On 2019/5/19
 */
public class Result241 {

    @SerializedName("pm2.5")
    private int _$Pm25277; // FIXME check this code
    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("co2")
    private int co2;
    @SerializedName("temperature")
    private int temperature;
    @SerializedName("humidity")
    private int humidity;
    @SerializedName("LightIntensity")
    private int LightIntensity;
    @SerializedName("RESULT")
    private String RESULT;

    public static Result241 objectFromData(String str) {

        return new Gson().fromJson(str, Result241.class);
    }

    public int get_$Pm25277() {
        return _$Pm25277;
    }

    public void set_$Pm25277(int _$Pm25277) {
        this._$Pm25277 = _$Pm25277;
    }

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getLightIntensity() {
        return LightIntensity;
    }

    public void setLightIntensity(int LightIntensity) {
        this.LightIntensity = LightIntensity;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }
}
