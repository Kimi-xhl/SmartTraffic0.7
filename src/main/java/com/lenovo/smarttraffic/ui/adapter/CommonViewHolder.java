package com.lenovo.smarttraffic.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lenovo.smarttraffic.InitApp;
import com.lenovo.smarttraffic.api.UrlUtils;

/**
 * The type Common view holder.
 */
public class CommonViewHolder {
    private SparseArray<View> array;//存放所有的view
    private View convertView;//视图

    /**
     * 构造函数
     *
     * @param context   the context
     * @param parent    父级布局
     * @param layoutRes 子项布局
     */
    public CommonViewHolder(Context context, ViewGroup parent, int layoutRes) {
        //初始化SparseArray
        this.array = new SparseArray<>();
        //初始化视图
        this.convertView = LayoutInflater.from(context).inflate(layoutRes, parent, false);
        //设置tag
        this.convertView.setTag(this);
    }

    /**
     * 获取ViewHolder
     *
     * @param context     the context
     * @param convertView the convert view
     * @param parent      the parent
     * @param layoutRes   the layout res
     * @return the common view holder
     */
    public static CommonViewHolder get(Context context, View convertView,
                                       ViewGroup parent, int layoutRes) {
        //如果convertView为空则返回一个新的视图
        if (convertView == null) {
            return new CommonViewHolder(context, parent, layoutRes);
        }//否则根据tag取出视图并返回
        return (CommonViewHolder) convertView.getTag();
    }

    /**
     * 获取视图
     *
     * @return the convert view
     */
    public View getConvertView() {
        return convertView;
    }


    /**
     * 获取视图
     *
     * @param viewId 子布局中控件的id
     * @return the view
     */
    public View getView(int viewId) {
        //从array中取出对应的view
        View view = array.get(viewId);
        if (view == null) {
            //如果view为空，则查找view
            view = convertView.findViewById(viewId);
            //将view存放到array中
            array.put(viewId, view);
        }

        return view;
    }

    /*——————————对控件进行属性设置——————————————*/

    /**
     * Sets text.
     *
     * @param viewId the view id
     * @param s      the s
     * @return the text
     */
    public CommonViewHolder setText(int viewId, String s) {
        TextView view = (TextView) getView(viewId);
        view.setText(s);
        return this;
    }
    public CommonViewHolder setEnable(int viewId, boolean enable) {
        TextView view = (TextView) getView(viewId);
        view.setEnabled(enable);
        return this;
    }

    /**
     * Sets img res.
     *
     * @param viewId the view id
     * @param resId  the res id
     * @return the img res
     */
    public CommonViewHolder setImgRes(int viewId, int resId) {
        ImageView view = (ImageView) getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public CommonViewHolder setGldRes(int viewId,String url) {
        ImageView view = (ImageView) getView(viewId);
        Glide.with(InitApp.getInstance()).load(UrlUtils.getImageUrl+url).into(view);
        return this;
    }
    public CommonViewHolder setTextColor(int viewId, int color) {
        TextView view = (TextView) getView(viewId);
        view.setTextColor(color);
        return this;
    }


    /**
     * Sets background res.
     *
     * @param viewId the view id
     * @param res    the res
     * @return the background res
     */
    public CommonViewHolder setBackgroundRes(int viewId, int res) {
        View view = getView(viewId);
        view.setBackgroundResource(res);
        return this;
    }

    /**
     * Sets img.
     *
     * @param viewId   the view id
     * @param drawable the drawable
     * @return the img
     */
    public CommonViewHolder setImg(int viewId, Drawable drawable) {
        ImageView view = (ImageView) getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public CommonViewHolder setImgbit(int viewId, Bitmap bitmap) {
        ImageView view = (ImageView) getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public CommonViewHolder setVisibility(int viewId, int status) {
        View view = (View) getView(viewId);
        view.setVisibility(status);
        return this;
    }

    /**
     * Sets checked.
     *
     * @param viewId    the view id
     * @param isChecked the is checked
     * @return the checked
     */
    public CommonViewHolder setChecked(int viewId, boolean isChecked) {
        CheckBox view = (CheckBox) getView(viewId);
        view.setChecked(isChecked);
        return this;
    }


    /*——————————对控件进行事件设置——————————————*/

    /**
     * Sets on click.
     *
     * @param viewId   the view id
     * @param listener the listener
     * @return the on click
     */
    public CommonViewHolder setOnClick(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public CommonViewHolder setLongClick(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    /**
     * Sets on checked.
     *
     * @param viewId   the view id
     * @param listener the listener
     * @return the on checked
     */
    public CommonViewHolder setOnChecked(int viewId, CompoundButton.OnCheckedChangeListener listener) {
        CheckBox view = (CheckBox) getView(viewId);
        view.setOnCheckedChangeListener(listener);
        return this;
    }

}
