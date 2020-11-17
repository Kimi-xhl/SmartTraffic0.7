package com.lenovo.smarttraffic;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.ui.activity.BaseActivity;
import com.lenovo.smarttraffic.ui.activity.Item1Activity;
import com.lenovo.smarttraffic.ui.activity.Item2Activity;
import com.lenovo.smarttraffic.ui.activity.Item3Activity;
import com.lenovo.smarttraffic.ui.activity.Item4Activity;
import com.lenovo.smarttraffic.ui.activity.Item5Activity;
import com.lenovo.smarttraffic.ui.activity.LoginActivity;
import com.lenovo.smarttraffic.ui.fragment.DesignFragment;
import com.lenovo.smarttraffic.ui.fragment.MainContentFragment;
import com.lenovo.smarttraffic.util.Util;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Amoly
 * @date 2019/4/11.
 * email：caoxl@lenovo.com
 * description：
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private CircleImageView mImageView;
    private MainContentFragment mMainContent;
    private DesignFragment mDesignFragment;
    private static final String POSITION = "position";
    private static final String SELECT_ITEM = "bottomItem";
    private static final int FRAGMENT_MAIN = 0;
    private static final int FRAGMENT_DESIGN = 1;
    private BottomNavigationView bottom_navigation;
    //    private int position;
    private TextView textViewUser;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initToolBar();
        if (savedInstanceState != null) {
            loadMultipleRootFragment(R.id.container, 0, mMainContent, mDesignFragment);
//            mMainContent = (MainContentFragment) getSupportFragmentManager().findFragmentByTag( MainContentFragment.class.getName());
//            mDesignFragment = (DesignFragment) getSupportFragmentManager().findFragmentByTag( DesignFragment.class.getName());
            // 恢复 recreate 前的位置
            showFragment(savedInstanceState.getInt(POSITION));
            bottom_navigation.setSelectedItemId(savedInstanceState.getInt(SELECT_ITEM));
        } else {
//            loadRootFragment(R.id.container, MainContentFragment.getInstance());
            showFragment(FRAGMENT_MAIN);
        }
    }


    private void showFragment(int index) {
//        position = index;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (index) {
            case FRAGMENT_MAIN:
                mToolbar.setTitle(R.string.title_main);
                if (mMainContent == null) {
                    mMainContent = MainContentFragment.getInstance();
                    ft.add(R.id.container, mMainContent, MainContentFragment.class.getName());
                } else {
                    ft.show(mMainContent);
                }
                break;
            case FRAGMENT_DESIGN:
                mToolbar.setTitle(R.string.creative_design);
                if (mDesignFragment == null) {
                    mDesignFragment = DesignFragment.getInstance();
                    ft.add(R.id.container, mDesignFragment, DesignFragment.class.getName());
                } else {
                    ft.show(mDesignFragment);
                }
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        // 如果不为空，就先隐藏起来
        if (mMainContent != null) {
            ft.hide(mMainContent);
        }
        if (mDesignFragment != null) {
            ft.hide(mDesignFragment);
        }
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mDrawer = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        bottom_navigation = findViewById(R.id.bottom_navigation);
        mImageView = mNavigationView.getHeaderView(0).findViewById(R.id.ivAvatar);
        textViewUser = mNavigationView.getHeaderView(0).findViewById(R.id.textView);
        setSupportActionBar(mToolbar);
        TextView textView= (TextView) mToolbar.getChildAt(0);
        textView.setText("主页");
        textView.setTextSize(20);
        textView.setTextColor(Color.WHITE);
        textView.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
        textView.setGravity(Gravity.CENTER_HORIZONTAL);

        textViewUser.setText(Util.getUser().getUserName());

    }

    private void initToolBar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        mDrawer.addDrawerListener(toggle);
        bottom_navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_main:
                    showFragment(FRAGMENT_MAIN);
                    break;
                case R.id.action_creative:
                    showFragment(FRAGMENT_DESIGN);
                    break;
            }
            return true;
        });
        /*设置选择item监听*/
        mNavigationView.setNavigationItemSelectedListener(this);
        mImageView.setOnClickListener(this);

    }

    @Override
    public void onBackPressedSupport() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {    /*打开或关闭左边的菜单*/
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressedSupport();
            showExitDialog();
        }
    }


    /*是否退出项目*/
    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出吗");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", (dialogInterface, i) -> finish());
        builder.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        /*设置选中item事件*/
        int id = item.getItemId();
        String string = null;
        switch (id) {
            case R.id.nav_account:
                string = "个人";
                break;
            case R.id.item_1:
                string = "item1";
                startActivity(new Intent(this, Item1Activity.class));
                break;
            case R.id.item_2:
                string = "item2";
                startActivity(new Intent(this, Item2Activity.class));
                break;
            case R.id.item_3:
                startActivity(new Intent(this, Item3Activity.class));
                break;
            case R.id.item_4:
                startActivity(new Intent(this, Item4Activity.class));
            case R.id.item_5:
                startActivity(new Intent(this, Item5Activity.class));
                break;
            case R.id.nav_setting:
                string = "设置";
                break;
            case R.id.nav_about:
                string = "关于";
                break;
        }
        if (!TextUtils.isEmpty(string))
            Toast.makeText(InitApp.getInstance(), "你点击了" + "\"" + string + "\"", Toast.LENGTH_SHORT).show();
//        mDrawer.closeDrawer(GravityCompat.START);
        mDrawer.closeDrawers();
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivAvatar) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
