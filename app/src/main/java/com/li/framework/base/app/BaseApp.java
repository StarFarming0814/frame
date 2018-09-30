package com.li.framework.base.app;

import com.li.net.HttpUtils;
import com.li.net.proxy.impl.OkHttpProcessor;
import com.qihoo360.replugin.RePluginApplication;

/**
 * @author Li
 * @since 2018/8/26
 */
public class BaseApp extends RePluginApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        HttpUtils.getInstance().register(new OkHttpProcessor());
    }
}
