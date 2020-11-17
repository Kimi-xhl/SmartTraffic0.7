package com.lenovo.smarttraffic.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 通用适配器
 *
 * @param <T> 数据类型(泛型)
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    private Context context;
    private List<T> list;
    private int layoutRes;


    /**
     * 构造函数
     *
     * @param context   上下文context
     * @param list      数据列表
     * @param layoutRes 子布局
     */
    public CommonAdapter(Context context, List<T> list, int layoutRes) {
        this.context = context;
        this.list = list;
        this.layoutRes = layoutRes;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取ViewHolder
        CommonViewHolder holder = CommonViewHolder.get(context, convertView, parent, layoutRes);
        //输出数据
        convert(holder, list.get(position),position);
        //返回视图
        return holder.getConvertView();
    }

    /**
     * 输出holder和数据
     *
     * @param holder   ViewHolder
     * @param item     子项数据
     * @param position 子项下标
     */
    public abstract void convert(CommonViewHolder holder, T item,int position);
}
