package com.li.framework.base.app;

import android.app.Application;

import com.li.net.HttpUtils;
import com.li.net.proxy.impl.OkHttpProcessor;

/**
 * @author Li
 * @since 2018/8/26
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HttpUtils.getInstance().register(new OkHttpProcessor());
    }
}
