package com.li.framework.base.presenter;

import com.li.framework.base.view.IBaseView;

import java.lang.ref.WeakReference;

/**
 * Presenter 的基类,通过弱引用{@link WeakReference} 持有一个{@link IBaseView} 的子类对象
 *
 * @param <T> 泛型,{@link IBaseView}的子类
 * @author Li
 * @since 2018/8/26
 */
public class BasePresenter<T extends IBaseView> {

    /**
     * 弱引用,持有一个{@link IBaseView} 的子类对象
     */
    protected WeakReference<T> weakReference;

    /**
     * 建立关联
     *
     * @param view {@link T}
     */
    public void attachView(T view) {
        weakReference = new WeakReference<>(view);
    }

    /**
     * 获取{@link T}
     *
     * @return {@link T}
     */
    public T getView() {
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    /**
     * UI展示相关的操作需要判断一下 Activity 是否已经 finish.
     * <p>
     * 只有当 isActivityAlive 返回true时才可以执行与Activity相关的操作,
     * 比如 弹出Dialog、Window、跳转Activity等操作.
     *
     * @return 布尔值
     */
    public boolean isViewAttached() {
        return weakReference != null && weakReference.get() != null;
    }

    /**
     * 解除关联
     */
    public void detachView() {
        weakReference.clear();
    }

}
