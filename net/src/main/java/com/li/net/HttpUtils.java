package com.li.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.li.net.adapter.DoubleDefault0Adapter;
import com.li.net.adapter.IntegerDefault0Adapter;
import com.li.net.adapter.LongDefault0Adapter;
import com.li.net.callback.ICallBack;
import com.li.net.proxy.IHttpProxy;

import java.util.Map;

/**
 * @author Li
 * @since 2018/8/26
 */
public class HttpUtils implements IHttpProxy {
    private static HttpUtils _instance;

    private IHttpProxy httpProxy;

    private HttpUtils() {
    }

    public static HttpUtils getInstance() {
        if (_instance == null) {
            synchronized (HttpUtils.class) {
                if (_instance == null) {
                    _instance = new HttpUtils();
                }
            }
        }
        return _instance;
    }

    public static Gson gson;

    private void buildGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                    .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                    .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                    .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                    .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                    .registerTypeAdapter(long.class, new LongDefault0Adapter())
                    .create();
        }
    }

    public void register(IHttpProxy httpProxy) {
        this.httpProxy = httpProxy;
        buildGson();
    }

    @Override
    public void get(String url, Map<String, String> param, Object tag, ICallBack callBack) {
        httpProxy.get(url, param, tag, callBack);
    }

    @Override
    public void post(String url, Map<String, String> param, Object tag, ICallBack callBack) {
        httpProxy.post(url, param, tag, callBack);
    }

    @Override
    public void download(String url, Map<String, String> param, String filePath, Object tag, ICallBack callBack) {
        httpProxy.download(url, param, filePath, tag, callBack);
    }

    @Override
    public void cancel(Object tag) {
        httpProxy.cancel(tag);
    }

    /**
     * 判断是否联网
     *
     * @param context 用 ApplicationContext
     * @return true:有网络 false: 无网络
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                .getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = null;
        if (mConnectivityManager != null) {
            mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        }
        return mNetworkInfo != null && mNetworkInfo.isAvailable();
    }

    /**
     * 判断当前网络是否是wifi网络
     * if(activeNetInfo.getType()==ConnectivityManager.TYPE_MOBILE) //判断3G网
     *
     * @param context {@link Context}
     * @return boolean
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = null;
        if (connectivityManager != null) {
            activeNetInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }
}
