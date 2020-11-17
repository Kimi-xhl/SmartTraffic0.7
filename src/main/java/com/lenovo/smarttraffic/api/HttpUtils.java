package com.lenovo.smarttraffic.api;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.lenovo.smarttraffic.InitApp;
import com.lenovo.smarttraffic.util.Util;

import org.json.JSONObject;

import java.util.Map;

public class HttpUtils {
    //回调接口
    public interface RequestListener<T> {
        void onSuccess(T result);

        void onFailure(String msg);
    }

    /**
     * Request.
     *
     * @param <T>      解析的类型(泛型)
     * @param url      请求地址
     * @param params   请求参数
     * @param tClass   解析的类型
     * @param listener 回调监听
     */
    public static <T> void request(String url, Map<String, String> params, final Class<T> tClass, final RequestListener<T> listener) {
        //默认添加UserName参数
        if (!params.containsKey("UserName")) {
            params.put("UserName", Util.getUser().getUserName());
        }
        Request request = new JsonObjectRequest(Request.Method.POST, url, new Gson().toJson(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        //获取RESULT
                        String result = jsonObject.optString("RESULT");
                        try {
                            T bean = new Gson().fromJson(jsonObject.toString(), tClass);
                            //根据服务器返回的数据进行对应的处理
                            if (result.equals("S")) {
                                listener.onSuccess(bean);
                            } else {
                                listener.onFailure(jsonObject.optString("ERRMSG"));
                            }
                        } catch (JsonParseException e) {
                            //数据解析异常
                            listener.onFailure("数据异常，请检查服务器");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        //打印请求失败的原因
                        if (!TextUtils.isEmpty(volleyError.getMessage())) {
                            Log.e("请求失败", volleyError.getMessage());
                        }

                        listener.onFailure("请求失败");
                    }
                }).setRetryPolicy(new DefaultRetryPolicy(7000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        );

        //将request加入请求队列
        InitApp.getQueue().add(request);
    }
}
