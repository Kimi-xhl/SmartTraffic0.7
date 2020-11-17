package com.lenovo.smarttraffic.ui.fragment;

import android.view.View;
import android.widget.ListView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.F1_news;
import com.lenovo.smarttraffic.ui.adapter.CommonAdapter;
import com.lenovo.smarttraffic.ui.adapter.CommonViewHolder;

import java.util.ArrayList;
import java.util.List;


public class OneFragment extends BaseFragment {


    private ListView mLv;
    private CommonAdapter<F1_news> adapter;
    private List<F1_news> list = new ArrayList<>();

    @Override
    protected View getSuccessView() {
        View view = View.inflate(getActivity(), R.layout.fragment_all, null);
        initView(view);
        return view;
    }

    @Override
    protected Object requestData() {

        return "";/*加载成功*/
    }

    @Override
    public void onClick(View view) {

    }


    private void initView(View view) {
        mLv = (ListView) view.findViewById(R.id.lv);
        adapter = new CommonAdapter<F1_news>(getActivity(),list,R.layout.fragment_all_item) {
            @Override
            public void convert(CommonViewHolder holder, F1_news item, int position) {
                holder.setText(R.id.tv_title, item.getTitle());
                holder.setText(R.id.tv_content, item.getContent());
                holder.setText(R.id.tv_date, item.getTime());
                holder.setGldRes(R.id.img,item.getImgurl());
            }
        };

        mLv.setAdapter(adapter);
    }
}
