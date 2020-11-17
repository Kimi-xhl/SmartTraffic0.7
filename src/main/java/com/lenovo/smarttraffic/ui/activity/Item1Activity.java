package com.lenovo.smarttraffic.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.j256.ormlite.dao.Dao;
import com.lenovo.smarttraffic.InitApp;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.F1;
import com.lenovo.smarttraffic.db.DB;
import com.lenovo.smarttraffic.ui.adapter.BasePagerAdapter;
import com.lenovo.smarttraffic.ui.fragment.BlankFragment;
import com.lenovo.smarttraffic.util.CommonUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;



public class Item1Activity extends BaseActivity {
    @BindView(R.id.tab_layout_list)
    TabLayout tabLayoutList;
    @BindView(R.id.add_channel_iv)
    ImageView addChannelIv;
    @BindView(R.id.header_layout)
    LinearLayout headerLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private Dao dao;
    private List<F1> lists = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitView();
        InitData();
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_list_tab;
    }


    private void InitView() {
        dao = DB.getInstance().getDao(F1.class);
        initToolBar(findViewById(R.id.toolbar), true, getString(R.string.item1));
        tabLayoutList.setupWithViewPager(viewPager);
        tabLayoutList.setTabMode(TabLayout.MODE_SCROLLABLE);
        headerLayout.setBackgroundColor(CommonUtil.getInstance().getColor());
    }

    private void InitData() {
        lists.clear();
        titles.clear();
        fragmentList.clear();

            try {
                lists = dao.queryBuilder().orderBy("id",true).where().eq("add", true).query();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < lists.size(); i++) {
                titles.add(lists.get(i).getTitle());
                fragmentList.add(BlankFragment.newInstance(lists.get(i).getTitle()));
            }

        BasePagerAdapter basePagerAdapter = new BasePagerAdapter(getSupportFragmentManager(),fragmentList,titles);
        viewPager.setAdapter(basePagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        addChannelIv.setVisibility(View.VISIBLE);
        addChannelIv.setOnClickListener(view -> {
            Intent intent = new Intent(InitApp.getInstance(), ChannelActivity.class);
            startActivity(intent);
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        headerLayout.setBackgroundColor(CommonUtil.getInstance().getColor());
        InitData();
    }


}
