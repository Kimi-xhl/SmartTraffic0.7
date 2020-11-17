package com.lenovo.smarttraffic.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.F1;
import com.lenovo.smarttraffic.db.DB;
import com.lenovo.smarttraffic.ui.adapter.CommonAdapter;
import com.lenovo.smarttraffic.ui.adapter.CommonViewHolder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChannelActivity extends BaseActivity {
    private GridView mGv1;
    private GridView mGv2;
    private List<String> list1 = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();

    private CommonAdapter<String> adapter1;
    private CommonAdapter<String> adapter2;
    private Dao dao;
    private List<F1> addList = new ArrayList<>();
    private List<F1> noList = new ArrayList<>();


    @Override
    protected int getLayout() {
        return R.layout.activity_channel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        initView();
        getData();
    }

    private void getData() {

        SharedPreferences preferences = getSharedPreferences("first1", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        try {
            addList = dao.queryBuilder().orderBy("id",true).where().eq("add", true).query();
            noList = dao.queryBuilder().orderBy("id",true).where().eq("add", false).query();
            if (preferences.getBoolean("first1", true)) {
                editor.putBoolean("first1", false);
                editor.apply();
                    list1.add("推荐");
                    list1.add("交通");
                    list1.add("科技");

                    list2.add("路况新闻");
                    list2.add("汽车分类");
                    list2.add("旅游");


                try {
                    dao.create(new F1("推荐", true));
                    dao.create(new F1("交通", true));
                    dao.create(new F1("科技", true));

                    dao.create(new F1("路况新闻", false));
                    dao.create(new F1("汽车分类", false));
                    dao.create(new F1("旅游", false));

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }else {
                for (int i = 0; i < addList.size(); i++) {
                    list1.add(addList.get(i).getTitle());
                }
                for (int i = 0; i < noList.size(); i++) {
                    list2.add(noList.get(i).getTitle());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
    }

    private void initView() {
        dao = DB.getInstance().getDao(F1.class);
        initToolBar(findViewById(R.id.toolbar), true, getString(R.string.title_item_main));

        mGv1 = (GridView) findViewById(R.id.gv1);
        mGv2 = (GridView) findViewById(R.id.gv2);

        adapter1 = new CommonAdapter<String>(this,list1,R.layout.activity01_item1) {
            @Override
            public void convert(CommonViewHolder holder, String item, int position) {
                holder.setText(R.id.tv_1, item);
                holder.setOnClick(R.id.btn_del, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            dao.create(new F1(list1.get(position),false));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        list2.add(list1.get(position));

                        try {
                            DeleteBuilder deleteBuilder = dao.deleteBuilder();
                            deleteBuilder.where().eq("add", true)
                                    .and().eq("title",list1.get(position));
                            deleteBuilder.delete();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        list1.remove(position);

                        adapter1.notifyDataSetChanged();
                        adapter2.notifyDataSetChanged();
                    }
                });
            }
        };

        mGv1.setAdapter(adapter1);

        adapter2 = new CommonAdapter<String>(this,list2,R.layout.activity01_item2) {
            @Override
            public void convert(CommonViewHolder holder, String item, int position) {
                holder.setText(R.id.tv_2, item);

                holder.setOnClick(R.id.btn_add, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            dao.create(new F1(list2.get(position),true));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        list1.add(list2.get(position));
                        try {
                            DeleteBuilder deleteBuilder = dao.deleteBuilder();
                            deleteBuilder.where().eq("add", false)
                                    .and().eq("title",list2.get(position));
                            deleteBuilder.delete();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        list2.remove(position);


                        adapter1.notifyDataSetChanged();
                        adapter2.notifyDataSetChanged();
                    }
                });
            }
        };

        mGv2.setAdapter(adapter2);

        mGv1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return true;
            }
        });

    }


}
