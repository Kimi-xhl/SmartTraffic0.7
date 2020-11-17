package com.lenovo.smarttraffic.ui.fragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author Amoly
 * @date 2019/4/11.
 * email：caoxl@lenovo.com
 * description：主页面
 */
public class MainContentFragment extends SupportFragment {
    private static MainContentFragment instance = null;

    public static MainContentFragment getInstance() {
        if (instance == null) {
            instance = new MainContentFragment();
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
