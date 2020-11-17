package com.lenovo.smarttraffic.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenovo.smarttraffic.ui.activity.BaseActivity;
import com.lenovo.smarttraffic.util.CommonUtil;
import com.lenovo.smarttraffic.widget.ContentPage;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author Amoly
 * @date 2019/4/11.
 * email：caoxl@lenovo.com
 * description：
 */
public abstract class BaseFragment extends SupportFragment implements View.OnClickListener {
    public ContentPage contentPage;
    public ProgressDialog pdLoading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
         * 初始化pdLoading
         */
        pdLoading = new ProgressDialog(getActivity());
        pdLoading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pdLoading.setMessage("请稍后");
        pdLoading.setCanceledOnTouchOutside(false);
        pdLoading.setCancelable(true);

        if (contentPage == null) {
            contentPage = new ContentPage(getActivity()) {
                @Override
                public Object loadData() {
                    return requestData();
                }

                @Override
                public View createSuccessView() {
                    return getSuccessView();
                }
            };
        } else {
            CommonUtil.removeSelfFromParent(contentPage);
        }
        return contentPage;
    }

    /**
     * 初始化 Toolbar
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        ((BaseActivity) getActivity()).initToolBar(toolbar, homeAsUpEnabled, title);
    }
    /**
     * 刷新状态
     *
     */
    public void refreshPage(Object o) {
        contentPage.refreshPage(o);
    }

    /**
     * 返回据的fragment填充的具体View
     */
    protected abstract View getSuccessView();

    /**
     * 返回请求服务器的数据
     */
    protected abstract Object requestData();


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}