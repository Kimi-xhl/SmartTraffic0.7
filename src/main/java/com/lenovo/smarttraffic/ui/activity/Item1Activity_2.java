package com.lenovo.smarttraffic.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.api.UrlUtils;
import com.lenovo.smarttraffic.bean.F1_news;

/**
 * author: LBX
 * date:   On 2019/5/31
 */
public class Item1Activity_2 extends BaseActivity {
    private TextView mTvCategory;
    private TextView mTvTime;
    private TextView mTvContent;
    private ImageView mImg;
    private F1_news f1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null) {
            f1 = (F1_news) getIntent().getSerializableExtra("f1");
        }
        initView();
        getData();
    }

    private void getData() {
            mTvCategory.setText("分类：" + f1.getCategory());
            mTvContent.setText(f1.getContent());
            Glide.with(this).load(UrlUtils.getImageUrl + f1.getImgurl()).into(mImg);
            mTvTime.setText("时间：" + f1.getTime());

    }

    @Override
    protected int getLayout() {
        return R.layout.activity01_2;
    }

    private void initView() {
        initToolBar(findViewById(R.id.toolbar), true,f1.getTitle());
        mTvCategory = (TextView) findViewById(R.id.tv_category);
        mTvTime = (TextView) findViewById(R.id.tv_time);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mImg = (ImageView) findViewById(R.id.img);
    }
}
