package com.lenovo.smarttraffic.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.InitApp;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.widget.ContentPage;

/**
 * author: LBX
 * date:   On 2019/5/19
 */
public class LoadingDialog {
    private static Toast toast;
    public static Dialog dialog;
    public ContentPage contentPage;


    public static void showDialog(Context context, String s) {
        dialog = new Dialog(context, R.style.custom_dialog2);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.page_loading);
        dialog.setCanceledOnTouchOutside(false);
        TextView textView = (TextView) dialog.findViewById(R.id.tv_loading);
        textView.setText(s);
        dialog.show();

    }

    public static void showToast(String msg) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(InitApp.getInstance(), msg, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void disDialog() {
        if (dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
