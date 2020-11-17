package com.lenovo.smarttraffic.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * author: LBX
 * date:   On 2019/5/22
 */
@DatabaseTable
public class F2 implements Serializable {
    @DatabaseField
    private String username;
    @DatabaseField
    private String name;
    @DatabaseField
    private String phone;
    @DatabaseField
    private String role;
    @DatabaseField
    private String sex;
    @DatabaseField
    private int collect;//1是收藏  0是未收藏
    @DatabaseField
    private long time;

    public F2() {
    }

    public F2(String username, String name, String phone, String role, String sex, int collect, long time) {
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.sex = sex;
        this.collect = collect;
        this.time = time;
    }

    public F2(String username, String name, String phone, String role, String sex) {
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
