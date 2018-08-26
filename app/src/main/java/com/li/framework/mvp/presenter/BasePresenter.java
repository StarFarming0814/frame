package com.li.framework.mvp.presenter;

import java.lang.ref.WeakReference;

/**
 * @author Li
 * @since 2018/8/26
 */
public class BasePresenter<T> {
    protected WeakReference<T> weakReference;

    public void attachView(T view){
        weakReference = new WeakReference<>(view);
    }

    public void detachView(){
        weakReference.clear();
    }
}
