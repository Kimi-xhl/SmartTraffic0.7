package com.lenovo.smarttraffic.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.F2;
import com.lenovo.smarttraffic.db.DB;
import com.lenovo.smarttraffic.ui.adapter.CommonAdapter;
import com.lenovo.smarttraffic.ui.adapter.CommonViewHolder;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * author: LBX
 * date:   On 2019/5/22
 */
public class Item2Activity_2 extends BaseActivity {

    private ListView mLv;
    private List<F2> list = new ArrayList<>();
    private List<F2> tempList = new ArrayList<>();
    private CommonAdapter<F2> adapter;
    private TextView mTvNull;
    private Dao dao;
    private Toolbar toolbar;

    @Override
    protected int getLayout() {
        return R.layout.activity02_2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity02_2);
        initView();
        getData();
    }

    private void getData() {
        try {
            tempList = dao.queryBuilder().where().eq("collect", 1).query();
            if (tempList.isEmpty()) {
                mTvNull.setVisibility(View.VISIBLE);
            } else {
                list.addAll(tempList);
                adapter.notifyDataSetChanged();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        dao = DB.getInstance().getDao(F2.class);
        initToolBar(findViewById(R.id.toolbar), true, getString(R.string.item2));
        mLv = (ListView) findViewById(R.id.lv);
        mTvNull = (TextView) findViewById(R.id.tv_null);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Item2Activity_2.this, Item2Activity.class);
                startActivity(intent);
                finish();
            }
        });

        adapter = new CommonAdapter<F2>(this, list, R.layout.activity02_2_item) {
            @Override
            public void convert(CommonViewHolder holder, F2 item, int position) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
                holder.setText(R.id.tv_username, "用户名：" + item.getUsername());
                holder.setText(R.id.tv_name, "姓名：" + item.getName());
                holder.setText(R.id.tv_tel, "电话：" + item.getPhone());
                holder.setText(R.id.tv_date, format.format(item.getTime()));
                if (item.getRole().equals("R01")) {
                    holder.setText(R.id.tv_role, "普通用户");
                } else if (item.getRole().equals("R02")) {
                    holder.setText(R.id.tv_role, "一般管理员");
                } else {
                    holder.setText(R.id.tv_role, "超级一般管理员");
                }

                if (item.getSex().equals("女")) {
                    holder.setImgRes(R.id.img_head, R.mipmap.touxiang_1);
                } else {
                    holder.setImgRes(R.id.img_head, R.mipmap.touxiang_2);
                }
            }
        };
        mLv.setAdapter(adapter);


    }
}
