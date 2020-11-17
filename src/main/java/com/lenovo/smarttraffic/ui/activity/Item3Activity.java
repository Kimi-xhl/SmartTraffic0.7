package com.lenovo.smarttraffic.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.lenovo.smarttraffic.InitApp;
import com.lenovo.smarttraffic.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * author: LBX
 * date:   On 2019/5/26
 */
public class Item3Activity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mBtnXueyuan;
    private LinearLayout mBtnLianx;
    private LinearLayout mBtnYiyuan;
    private LinearLayout mBtnXingfu;
    private LinearLayout mBtnKuaisu;
    private LinearLayout mBtnGaosu;
    private LinearLayout mBtnParking;
    private BarChart mChart;
    private Timer timer;
    private List<List<Integer>> lists = new ArrayList<>();
    private int count;

    private List<BarEntry> yVals1 = new ArrayList<>();
    private List<BarEntry> yVals2 = new ArrayList<>();
    private List<BarEntry> yVals3 = new ArrayList<>();
    private List<BarEntry> yVals4 = new ArrayList<>();
    private List<BarEntry> yVals5 = new ArrayList<>();
    private List<BarEntry> yVals6 = new ArrayList<>();
    private List<BarEntry> yVals7 = new ArrayList<>();
    private List<IBarDataSet> dataSets;

    private boolean[] enable = new boolean[]{false, false, false, false, false, false, false};


    @Override
    protected int getLayout() {
        return R.layout.activity03;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity03);
        initView();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getData();
            }
        }, 10, 3000);

    }

    private void getData() {
        lists.clear();
        count = 0;
        for (int i = 0; i < 7; i++) {
            lists.add(new ArrayList<>());
            for (int j = 0; j < 7; j++) {
                count++;
                lists.get(i).add(new Random().nextInt(5)+1);
                if (count == 49) {
                    Item3Activity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initChart();
                        }
                    });
                }
            }
        }
    }

    private void initChart() {
        yVals1.clear();
        yVals2.clear();
        yVals3.clear();
        yVals4.clear();
        yVals5.clear();
        yVals6.clear();
        yVals7.clear();
        List<String> xVals = new ArrayList<>();
        xVals.add("周一");
        xVals.add("周二");
        xVals.add("周三");
        xVals.add("周四");
        xVals.add("周五");
        xVals.add("周六");
        xVals.add("周日");

        for (int i = 0; i < xVals.size(); i++) {
            yVals1.add(new BarEntry(i, lists.get(0).get(i)));
            yVals2.add(new BarEntry(i, lists.get(1).get(i)));
            yVals3.add(new BarEntry(i, lists.get(2).get(i)));
            yVals4.add(new BarEntry(i, lists.get(3).get(i)));
            yVals5.add(new BarEntry(i, lists.get(4).get(i)));
            yVals6.add(new BarEntry(i, lists.get(5).get(i)));
            yVals7.add(new BarEntry(i, lists.get(6).get(i)));
        }

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(20f);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
        xAxis.setGranularity(1);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(xVals.size());
        xAxis.setCenterAxisLabels(true);
        xAxis.setLabelCount(xVals.size());

        List<String> status = new ArrayList<>();
        status.add("畅通");
        status.add("缓行");
        status.add("一般拥堵");
        status.add("中度拥堵");
        status.add("严重拥堵");

        YAxis yAxis1 = mChart.getAxisLeft();
        yAxis1.setTextSize(10);
        yAxis1.setAxisMinimum(0);

        yAxis1.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                switch ((int) value) {
                    case 1:
                        return "畅通";
                    case 2:
                        return "缓行";
                    case 3:
                        return "一般拥堵";
                    case 4:
                        return "中度拥堵";
                    case 5:
                        return "严重拥堵";
                }
                return "";
            }
        });
        yAxis1.setGranularity(1f);
        yAxis1.setCenterAxisLabels(true);

        YAxis yAxis2 = mChart.getAxisRight();
        yAxis2.setTextSize(10f);
        yAxis2.setAxisMinimum(0);
        yAxis2.setGranularity(1f);
        yAxis2.setCenterAxisLabels(true);
        yAxis2.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return new DecimalFormat("0").format(v);
            }
        });

        List<Integer> colors = new ArrayList<>();
        colors.add(ContextCompat.getColor(InitApp.getInstance(), R.color.f3_1));
        colors.add(ContextCompat.getColor(InitApp.getInstance(), R.color.f3_2));
        colors.add(ContextCompat.getColor(InitApp.getInstance(), R.color.f3_3));
        colors.add(ContextCompat.getColor(InitApp.getInstance(), R.color.f3_4));
        colors.add(ContextCompat.getColor(InitApp.getInstance(), R.color.f3_5));
        colors.add(ContextCompat.getColor(InitApp.getInstance(), R.color.f3_6));
        colors.add(ContextCompat.getColor(InitApp.getInstance(), R.color.f3_7));

        BarDataSet set1 = new BarDataSet(yVals1, "学院路");
        set1.setDrawValues(false);
        BarDataSet set2 = new BarDataSet(yVals2, "联想路");
        set2.setDrawValues(false);
        BarDataSet set3 = new BarDataSet(yVals3, "医院路");
        set3.setDrawValues(false);
        BarDataSet set4 = new BarDataSet(yVals4, "幸福路");
        set4.setDrawValues(false);
        BarDataSet set5 = new BarDataSet(yVals5, "环城快速路");
        set5.setDrawValues(false);
        BarDataSet set6 = new BarDataSet(yVals6, "环城高速");
        set6.setDrawValues(false);
        BarDataSet set7 = new BarDataSet(yVals7, "停车场");
        set7.setDrawValues(false);

        if (enable[0] == false) {
            set1.setColors(colors.get(0));
        } else if (enable[0]) {
            set1.setColor(0);
        }
        if (enable[1] == false) {
            set2.setColors(colors.get(1));
        } else if (enable[1]) {
            set2.setColor(0);
        }
        if (enable[2] == false) {
            set3.setColors(colors.get(2));
        } else if (enable[2]) {
            set3.setColor(0);
        }
        if (enable[3] == false) {
            set4.setColors(colors.get(3));
        } else if (enable[3]) {
            set4.setColor(0);
        }
        if (enable[4] == false) {
            set5.setColors(colors.get(4));
        } else if (enable[4]) {
            set5.setColor(0);
        }
        if (enable[5] == false) {
            set6.setColors(colors.get(5));
        } else if (enable[5]) {
            set6.setColor(0);
        }
        if (enable[6] == false) {
            set7.setColors(colors.get(6));
        } else if (enable[6]) {
            set7.setColor(0);
        }
        dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);
        dataSets.add(set4);
        dataSets.add(set5);
        dataSets.add(set6);
        dataSets.add(set7);

        BarData data = new BarData(dataSets);
        int barcount = dataSets.size();
        float groupspace = 0.1f;
        float barwidth = (1f - groupspace) / barcount;
        float barspace = 0f;

        data.setBarWidth(barwidth);
        data.groupBars(0f, groupspace, barspace);

        mChart.getLegend().setEnabled(false);
        mChart.setScaleEnabled(false);
        mChart.setDescription(null);
        mChart.setExtraOffsets(30, 0, 30, 30);
        mChart.setData(data);
        mChart.animateY(500);
        mChart.invalidate();
    }

    private void initView() {
        initToolBar(findViewById(R.id.toolbar), true, getString(R.string.item2));

        mBtnXueyuan = (LinearLayout) findViewById(R.id.btn_xueyuan);
        mBtnXueyuan.setOnClickListener(this);
        mBtnLianx = (LinearLayout) findViewById(R.id.btn_lianx);
        mBtnLianx.setOnClickListener(this);
        mBtnYiyuan = (LinearLayout) findViewById(R.id.btn_yiyuan);
        mBtnYiyuan.setOnClickListener(this);
        mBtnXingfu = (LinearLayout) findViewById(R.id.btn_xingfu);
        mBtnXingfu.setOnClickListener(this);
        mBtnKuaisu = (LinearLayout) findViewById(R.id.btn_kuaisu);
        mBtnKuaisu.setOnClickListener(this);
        mBtnGaosu = (LinearLayout) findViewById(R.id.btn_gaosu);
        mBtnGaosu.setOnClickListener(this);
        mBtnParking = (LinearLayout) findViewById(R.id.btn_parking);
        mBtnParking.setOnClickListener(this);
        mChart = (BarChart) findViewById(R.id.chart);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_xueyuan:
                yVals1.clear();
                enable[0] = true;
                mChart.invalidate();
                break;
            case R.id.btn_lianx:
                yVals2.clear();
                enable[1] = true;
                mChart.invalidate();
                break;
            case R.id.btn_yiyuan:
                yVals3.clear();
                enable[2] = true;
                mChart.invalidate();
                break;
            case R.id.btn_xingfu:
                yVals4.clear();
                enable[3] = true;
                mChart.invalidate();
                break;
            case R.id.btn_kuaisu:
                yVals5.clear();
                enable[4] = true;
                mChart.invalidate();
                break;
            case R.id.btn_gaosu:
                yVals6.clear();
                enable[5] = true;
                mChart.invalidate();
                break;
            case R.id.btn_parking:
                yVals7.clear();
                enable[6] = true;
                mChart.invalidate();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
