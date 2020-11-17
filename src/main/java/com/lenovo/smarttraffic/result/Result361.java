package com.lenovo.smarttraffic.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * author: LBX
 * date:   On 2019/5/22
 */
public class Result361 {

    /**
     * ERRMSG : 成功
     * UserRole : R02
     * RESULT : S
     */

    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("UserRole")
    private String UserRole;
    @SerializedName("RESULT")
    private String RESULT;

    public static Result361 objectFromData(String str) {

        return new Gson().fromJson(str, Result361.class);
    }

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String UserRole) {
        this.UserRole = UserRole;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }
}
