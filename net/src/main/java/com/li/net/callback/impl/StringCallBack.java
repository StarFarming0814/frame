package com.li.net.callback.impl;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.li.net.HttpUtils;
import com.li.net.callback.ICallBack;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Li
 * @since 2018/8/26
 */
public abstract class StringCallBack<T> implements ICallBack {

    private Handler handler = new Handler(Looper.getMainLooper());

    private String TAG = getClass().getSimpleName();

    /**
     * 运行在子线程
     *
     * @param string 联网成功获取数据
     */
    @Override
    public final void onSuccess(String string) {
        Log.d(TAG, string);
        Class<?> cls = analysisClazzInfo();
        if (cls != null) {
            final T t = (T) HttpUtils.gson.fromJson(string, cls);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    onSuccess(t);
                }
            });
        }
    }

    /**
     * 运行在主线程
     *
     * @param t 实体类
     */
    public abstract void onSuccess(T t);

    public abstract void onFailed(String error);

    /**
     * 获取泛型类{@link T}类的Class
     *
     * @return 返回 泛型类 的 Class
     */
    private Class<?> analysisClazzInfo() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (params[0] instanceof Class<?>) {
            return (Class<?>) params[0];
        }
        return null;
    }

    @Override
    public final void onFailure(final String error) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onFailed(error);
            }
        });
    }

    @Override
    public final void onFinish() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onFinished();
            }
        });
    }

    protected abstract void onFinished();
}
