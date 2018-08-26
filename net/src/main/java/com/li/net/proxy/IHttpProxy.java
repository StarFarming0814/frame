package com.li.net.proxy;

import com.li.net.callback.ICallBack;

import java.util.Map;

/**
 * @author Li
 * @since 2018/8/26
 */
public interface IHttpProxy {

    void get(String url, Map<String, String> param, Object tag, ICallBack callBack);

    void post(String url, Map<String, String> param, Object tag, ICallBack callBack);

    void cancel(Object tag);
}
