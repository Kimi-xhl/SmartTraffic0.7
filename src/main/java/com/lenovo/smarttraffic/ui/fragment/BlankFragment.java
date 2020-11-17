package com.lenovo.smarttraffic.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.api.HttpUtils;
import com.lenovo.smarttraffic.api.UrlUtils;
import com.lenovo.smarttraffic.bean.F1_news;
import com.lenovo.smarttraffic.result.Result2161;
import com.lenovo.smarttraffic.result.Result2171;
import com.lenovo.smarttraffic.ui.activity.Item1Activity_2;
import com.lenovo.smarttraffic.ui.adapter.CommonAdapter;
import com.lenovo.smarttraffic.ui.adapter.CommonViewHolder;
import com.lenovo.smarttraffic.util.LoadingDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView mLv;
    private CommonAdapter<F1_news> adapter;
    private List<F1_news> list = new ArrayList<>();
    private int category = 0;
    private SwipeRefreshLayout mRefresh;
    private TextView mTvToast;
    /*private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;*/


    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_all, container, false);
        initView(view);
        getData();
        return view;
    }

    private void getData() {
        list.clear();
        switch (mParam1) {
            case "推荐":
                category = 0;
                getNews(category);
                getSpot();
                break;
            case "交通":
                category = 1;
                getNews(category);
                getSpot();
                break;
            case "科技":
                category = 2;
                getNews(category);
                getSpot();
                break;
            case "路况新闻":
                category = 3;
                getNews(category);
                getSpot();
                break;
            case "汽车分类":
                category = 4;
                getNews(category);
                getSpot();
                break;
            case "旅游":
                category = 5;
                getSpot();
                break;
        }

    }

    private void getSpot() {
        HttpUtils.request(UrlUtils.url2161, new HashMap<>(), Result2161.class, new HttpUtils.RequestListener<Result2161>() {
            @Override
            public void onSuccess(Result2161 result) {
                long time = System.currentTimeMillis();
                for (Result2161.ROWSDETAILBean row : result.getROWSDETAIL()) {
                    list.add(new F1_news(row.getName(), row.getInfo(), row.getImg(), getTime(time), mParam1,time));
                }

                Collections.sort(list, new Comparator<F1_news>() {
                    @Override
                    public int compare(F1_news o1, F1_news o2) {
                        return (int) (o2.getLongtime() - o1.getLongtime());
                    }
                });
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    private String getTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(time);
    }

    private void getNews(int category) {
        //LoadingDialog.showDialog(getActivity(),"正在加载");
        list.clear();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Map<String, String> map = new HashMap<>();
        map.put("Category", String.valueOf(category));
        HttpUtils.request(UrlUtils.url2171, map, Result2171.class, new HttpUtils.RequestListener<Result2171>() {
            @Override
            public void onSuccess(Result2171 result) {
                for (Result2171.ROWSDETAILBean row : result.getROWSDETAIL()) {
                    try {
                        list.add(new F1_news(row.getTitle(), row.getContent(), "", row.getCreatetime(), mParam1,dateFormat.parse(row.getCreatetime()).getTime()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                Collections.sort(list, new Comparator<F1_news>() {
                    @Override
                    public int compare(F1_news o1, F1_news o2) {
                        return (int) (o2.getLongtime() - o1.getLongtime());
                    }
                });
                adapter.notifyDataSetChanged();
                LoadingDialog.disDialog();
            }

            @Override
            public void onFailure(String msg) {

            }
        })  ;
    }

    private void initView(View view) {
        mLv = (ListView) view.findViewById(R.id.lv);
        mRefresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        mTvToast = (TextView) view.findViewById(R.id.tv_toast);

        adapter = new CommonAdapter<F1_news>(getActivity(), list, R.layout.fragment_all_item) {
            @Override
            public void convert(CommonViewHolder holder, F1_news item, int position) {
                holder.setText(R.id.tv_title, item.getTitle());
                holder.setText(R.id.tv_content, item.getContent());
                holder.setText(R.id.tv_date, item.getTime());
                holder.setGldRes(R.id.img, item.getImgurl());
            }
        };
        mLv.setAdapter(adapter);

        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                F1_news f1 = list.get(position);
                Intent intent = new Intent(getActivity(), Item1Activity_2.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("f1", f1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.addAll(list.subList(0, 2));
               /* List<F1_news> tempList = new ArrayList<>();
                tempList.addAll(list);
                tempList.addAll(list.subList(0, 2));
                Collections.sort(tempList, new Comparator<F1_news>() {
                    @Override
                    public int compare(F1_news o1, F1_news o2) {
                        return (int) (o2.getLongtime() - o1.getLongtime());
                    }
                });

                list.addAll(tempList);*/
                Collections.sort(list, new Comparator<F1_news>() {
                    @Override
                    public int compare(F1_news o1, F1_news o2) {
                        return (int) (o2.getLongtime() - o1.getLongtime());
                    }
                });

                adapter.notifyDataSetChanged();


                mTvToast.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefresh.setRefreshing(false);
                        mTvToast.setVisibility(View.INVISIBLE);
                    }
                },1000);
            }
        });

        mLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0) {
                    mRefresh.setEnabled(true);
                }else {
                    mRefresh.setEnabled(false);
                }
            }
        });

    }

/*
    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                switch (networkInfo.getType()) {
                    case TYPE_MOBILE:
                        break;
                    case TYPE_WIFI:
                        break;
                    default:
                        break;
                }
            }else {

            }
        }
    }
*/


}
