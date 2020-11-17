package com.lenovo.smarttraffic.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenovo.smarttraffic.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author Amoly
 * @date 2019/4/11.
 * email：caoxl@lenovo.com
 * description：
 */
public class DesignFragment extends SupportFragment {
    private static DesignFragment instance = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.design_fragment, container, false);
        return view;
    }

    public static DesignFragment getInstance() {
        if (instance == null) {
            instance = new DesignFragment();
        }
        return instance;
    }




    @Override
    public void onDestroy() {
        if (instance != null) {
            instance = null;
        }
        super.onDestroy();
    }
}
