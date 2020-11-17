package com.lenovo.smarttraffic.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * author: LBX
 * date:   On 2019/4/3
 */
public class Result271 {

    /**
     * Status : 1
     * RESULT : S
     * ERRMSG : 成功
     */

    @SerializedName("Status")
    private int Status;
    @SerializedName("RESULT")
    private String RESULT;
    @SerializedName("ERRMSG")
    private String ERRMSG;

    public static Result271 objectFromData(String str) {

        return new Gson().fromJson(str, Result271.class);
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }
}
