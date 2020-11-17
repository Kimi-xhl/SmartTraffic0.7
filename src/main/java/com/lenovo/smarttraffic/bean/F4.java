package com.lenovo.smarttraffic.bean;

/**
 * author: LBX
 * date:   On 2019/5/26
 */
public class F4 {
    private String carid;
    private String period;
    private boolean peccancy = false;

    public F4(String carid, String period) {
        this.carid = carid;
        this.period = period;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public boolean isPeccancy() {
        return peccancy;
    }

    public void setPeccancy() {
        this.peccancy = true;
    }
}
