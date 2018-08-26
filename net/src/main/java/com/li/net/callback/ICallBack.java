package com.li.net.callback;

/**
 * @author Li
 * @since 2018/8/26
 */
public interface ICallBack {

    void onFailure(String error);

    void onSuccess(String result);

    void onFinish();

}
