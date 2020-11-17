package com.lenovo.smarttraffic.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.api.HttpUtils;
import com.lenovo.smarttraffic.api.UrlUtils;
import com.lenovo.smarttraffic.bean.F4;
import com.lenovo.smarttraffic.result.Result215;
import com.lenovo.smarttraffic.result.Result321;
import com.lenovo.smarttraffic.result.Result331;
import com.lenovo.smarttraffic.util.LoadingDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * author: LBX
 * date:   On 2019/5/26
 */
public class Item4Activity extends BaseActivity {

    private RadarChart mChart;
    private List<Result215.ROWSDETAILBean> peccancyinfo = new ArrayList<>();
    private List<Result321.ROWSDETAILBean> carinfo = new ArrayList<>();
    private List<Result331.ROWSDETAILBean> userinfo = new ArrayList<>();
    private List<F4> userList = new ArrayList<>();
    private Set<String> wzcarnum = new HashSet<>();
    private Set<String> wzcarid = new HashSet<>();
    private List<List<List<F4>>> lists = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getData();
    }

    private void getData() {
        HttpUtils.request(UrlUtils.url215, new HashMap<>(), Result215.class, new HttpUtils.RequestListener<Result215>() {
            @Override
            public void onSuccess(Result215 result) {
                peccancyinfo.addAll(result.getROWSDETAIL());
                isFinish();
            }

            @Override
            public void onFailure(String msg) {
                LoadingDialog.showToast(msg);
            }
        });
        HttpUtils.request(UrlUtils.url321, new HashMap<>(), Result321.class, new HttpUtils.RequestListener<Result321>() {
            @Override
            public void onSuccess(Result321 result) {
                carinfo.addAll(result.getROWSDETAIL());
                isFinish();
            }

            @Override
            public void onFailure(String msg) {
                LoadingDialog.showToast(msg);
            }
        });
        HttpUtils.request(UrlUtils.url331, new HashMap<>(), Result331.class, new HttpUtils.RequestListener<Result331>() {
            @Override
            public void onSuccess(Result331 result) {
                userinfo.addAll(result.getROWSDETAIL());
                isFinish();
            }

            @Override
            public void onFailure(String msg) {
                LoadingDialog.showToast(msg);
            }
        });
    }

    private void isFinish() {
        if (!peccancyinfo.isEmpty() && !carinfo.isEmpty() && !userinfo.isEmpty()) {
            LoadingDialog.showToast("加载完成");

            for (int i = 0; i < userinfo.size(); i++) {
                userList.add(new F4(userinfo.get(i).getPcardid(), userinfo.get(i).getPcardid().substring(8, 9)));
            }

            for (int i = 0; i < peccancyinfo.size(); i++) {
                wzcarnum.add(peccancyinfo.get(i).getCarnumber());
            }

            for (String s : wzcarnum) {
                for (int i = 0; i < carinfo.size(); i++) {
                    if (s.equals(carinfo.get(i).getCarnumber())) {
                        wzcarid.add(carinfo.get(i).getPcardid());
                    }
                }
            }

            for (int i = 0; i < userList.size(); i++) {
                for (String c : wzcarid) {
                    if (c.equals(userList.get(i).getCarid())) {
                        userList.get(i).setPeccancy();
                    }
                }
            }

            initChart();
        }
    }

    private void initChart() {
        List<String> xVals = new ArrayList<>();
        xVals.add("90后");
        xVals.add("80后");
        xVals.add("70后");
        xVals.add("60后");
        xVals.add("50后");

        List<RadarEntry> yVals1 = new ArrayList<>();
        List<RadarEntry> yVals2 = new ArrayList<>();


        for (int i = 0; i < xVals.size(); i++) {
            lists.add(new ArrayList<>());
            lists.get(i).add(new ArrayList<>());
            lists.get(i).add(new ArrayList<>());
        }

        for (int i = 0; i < userList.size(); i++) {
            F4 item = userList.get(i);
            int index = 0;
            if (item.isPeccancy()) {
                index = 1;
            }

            switch (item.getPeriod()) {
                case "9":
                    lists.get(0).get(index).add(item);
                    break;
                case "8":
                    lists.get(1).get(index).add(item);
                    break;
                case "7":
                    lists.get(2).get(index).add(item);
                    break;
                case "6":
                    lists.get(3).get(index).add(item);
                    break;
                case "5":
                    lists.get(4).get(index).add(item);
                    break;
            }
        }

        for (int i = 0; i < lists.size(); i++) {
            int no = lists.get(i).get(0).size();
            int have = lists.get(i).get(1).size();
            yVals1.add(new RadarEntry(no));
            yVals2.add(new RadarEntry(have));
        }

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(100);
        xAxis.setTextColor(Color.RED);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));

        YAxis yAxis = mChart.getYAxis();
        yAxis.setLabelCount(6,false);
        yAxis.setTextSize(15);
        yAxis.setAxisMinimum(0);


        RadarDataSet set1 = new RadarDataSet(yVals1, "");
        set1.setFillColor(Color.BLUE);
        set1.setDrawFilled(true);
        set1.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return new DecimalFormat("0.00%").format(value/yVals1.get((int) entry.getX()).getY());
            }
        });
        RadarDataSet set2 = new RadarDataSet(yVals2, "");
        set2.setFillColor(Color.GREEN);
        set2.setDrawFilled(true);
        set2.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return new DecimalFormat("0.00%").format(value/yVals2.get((int) entry.getX()).getY());
            }
        });

        List<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(sets);
        data.setValueTextSize(8f);
        data.setDrawValues(false);

        mChart.setDescription(null);
        mChart.setData(data);
        mChart.setExtraOffsets(0, 50, 0, 0);
        mChart.invalidate();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity04;
    }

    private void initView() {
        initToolBar(findViewById(R.id.toolbar), true, getString(R.string.item2));
        mChart = (RadarChart) findViewById(R.id.chart);
    }
}
