package com.lenovo.smarttraffic.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * author: LBX
 * date:   On 2019/5/21
 */
@DatabaseTable
public class F1 {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String title;
    @DatabaseField
    private boolean add =false;

    public F1() {
    }

    public F1(String title, boolean add) {
        this.title = title;
        this.add = add;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = true;
    }
}
