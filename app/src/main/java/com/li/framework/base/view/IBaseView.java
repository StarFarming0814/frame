package com.li.framework.base.view;

import android.content.Context;

/**
 * @author Li
 * @since 2018/8/26
 */
public interface IBaseView {

    /**
     * 展示 loading 弹框
     */
    void showLoading();

    /**
     * 关闭 loading 弹框
     */
    void dismissLoading();

    void toast(String msg);

    void toast(int stringRes);
}
