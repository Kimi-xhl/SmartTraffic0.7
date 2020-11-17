package com.lenovo.smarttraffic.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lenovo.smarttraffic.R;

/**
 * author: LBX
 * date:   On 2019/5/30
 */
public class Item5Activity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }
    @Override
    protected int getLayout() {
        return R.layout.activity05;
    }

    private void initView() {
        initToolBar(findViewById(R.id.toolbar), true, getString(R.string.item1));
    }
}
