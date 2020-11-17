package com.lenovo.smarttraffic.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.api.HttpUtils;
import com.lenovo.smarttraffic.api.UrlUtils;
import com.lenovo.smarttraffic.bean.F2;
import com.lenovo.smarttraffic.db.DB;
import com.lenovo.smarttraffic.result.Result331;
import com.lenovo.smarttraffic.result.Result361;
import com.lenovo.smarttraffic.ui.adapter.CommonAdapter;
import com.lenovo.smarttraffic.ui.adapter.CommonViewHolder;
import com.lenovo.smarttraffic.util.LoadingDialog;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: LBX
 * date:   On 2019/5/19
 */
public class Item2Activity extends BaseActivity implements View.OnClickListener {
    private TextView mTvDetails;
    private ListView mLv;
    private List<F2> list = new ArrayList<>();
    private List<F2> tempList = new ArrayList<>();
    private CommonAdapter<F2> adapter;
    private List<Result331.ROWSDETAILBean> userinfo = new ArrayList<>();
    private Dao dao;


    @Override
    protected int getLayout() {
        return R.layout.activity02;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity02);
        initView();
        getData();
    }


    private void getData() {
        try {
            tempList = dao.queryForAll();
            if (tempList.isEmpty()) {
                LoadingDialog.showDialog(this,"请求中");
                HttpUtils.request(UrlUtils.url331, new HashMap<>(), Result331.class, new HttpUtils.RequestListener<Result331>() {
                    @Override
                    public void onSuccess(Result331 result) {
                        userinfo.addAll(result.getROWSDETAIL());
                        getRole();
                        LoadingDialog.showToast("请求成功");
                        LoadingDialog.disDialog();
                    }

                    @Override
                    public void onFailure(String msg) {
                        LoadingDialog.showToast(msg);
                        LoadingDialog.disDialog();
                    }
                });
            }else {
                list.addAll(tempList);
                adapter.notifyDataSetChanged();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getRole() {
        for (Result331.ROWSDETAILBean row : userinfo) {
            Map<String, String> map = new HashMap<>();
            map.put("UserName", row.getUsername());
            map.put("UserPwd", "123456");

            HttpUtils.request(UrlUtils.url361, map, Result361.class, new HttpUtils.RequestListener<Result361>() {
                @Override
                public void onSuccess(Result361 result) {
                    list.add(new F2(row.getUsername(),row.getPname(),row.getPtel(),result.getUserRole(),row.getPsex()));
                    try {
                        dao.create(new F2(row.getUsername(),row.getPname(),row.getPtel(),result.getUserRole(),row.getPsex(),0,System.currentTimeMillis()));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(String msg) {
                    LoadingDialog.showToast(msg);
                }
            });
        }

    }

    private void initView() {
        dao = DB.getInstance().getDao(F2.class);
        initToolBar(findViewById(R.id.toolbar), true, getString(R.string.item2));
        mTvDetails = (TextView) findViewById(R.id.tv_details);
        mTvDetails.setOnClickListener(this);
        mLv = (ListView) findViewById(R.id.lv);


        adapter = new CommonAdapter<F2>(this, list, R.layout.activity02_item) {
            @Override
            public void convert(CommonViewHolder holder, F2 item, int position) {

                holder.setText(R.id.tv_username, "用户名：" + item.getUsername());
                holder.setText(R.id.tv_name, "姓名：" + item.getName());
                holder.setText(R.id.tv_tel, "电话：" + item.getPhone());
                holder.setOnClick(R.id.tv_collect, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            UpdateBuilder updateBuilder = dao.updateBuilder();
                            updateBuilder.where().eq("username", item.getUsername());
                            updateBuilder.updateColumnValue("time",System.currentTimeMillis());
                            updateBuilder.updateColumnValue("collect", 1);
                            updateBuilder.update();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        holder.setText(R.id.tv_collect, "已收藏");
                        holder.setEnable(R.id.tv_collect, false);
                        LoadingDialog.showToast("收藏成功");

                    }
                });
                holder.setOnClick(R.id.tv_delete, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            DeleteBuilder deleteBuilder = dao.deleteBuilder();
                            deleteBuilder.where().eq("username", item.getUsername());
                            deleteBuilder.delete();
                            list.remove(position);
                            adapter.notifyDataSetChanged();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                if (item.getCollect() == 1) {
                    holder.setText(R.id.tv_collect, "已收藏");
                    holder.setEnable(R.id.tv_collect, false);
                }else {
                    holder.setText(R.id.tv_collect, "收藏");
                    holder.setEnable(R.id.tv_collect, true);
                }

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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_details:
                Intent intent = new Intent(Item2Activity.this,Item2Activity_2.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
