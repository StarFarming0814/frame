package com.li.net.callback;

/**
 * @author Li
 * @since 2018/8/26
 */
public interface ICallBack {

    /**
     * 成功回调
     *
     * @param result json 字符串
     */
    void onSuccess(String result);

    /**
     * 失败回调
     *
     * @param error 错误信息
     */
    void onFailure(String error);

    /**
     * 完成回调
     */
    void onFinish();

}
