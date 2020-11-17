package com.lenovo.smarttraffic.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * author: LBX
 * date:   On 2019/5/21
 */
public class Result {

    /**
     * RESULT : S
     * ERRMSG : 成功
     */

    @SerializedName("RESULT")
    private String RESULT;
    @SerializedName("ERRMSG")
    private String ERRMSG;

    public static Result objectFromData(String str) {

        return new Gson().fromJson(str, Result.class);
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
