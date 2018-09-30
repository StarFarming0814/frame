package com.li.framework.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.li.framework.base.presenter.BasePresenter;
import com.li.framework.base.view.IBaseView;

/**
 * MVP架构的 Activity 基类,关联M-V-P
 * <p>
 * 继承自{@link BaseActivity},并实现{@link IBaseView}
 * 重写{@link IBaseView#showLoading()}和{@link IBaseView#dismissLoading()}方法
 * <p>
 * 持有一个{@linkplain BasePresenter 对象}
 * 在子类中实现{@link #createPresenter()}中进行创建
 * 并在{@link #onCreate(Bundle)}方法里进行{@link BasePresenter} 和 {@link IBaseView}的绑定
 * 在{@link #onDestroy()}中进行解绑
 *
 * @param <V> 泛型,属于{@linkplain IBaseView 的子类}
 * @param <T> 泛型,属于{@linkplain BasePresenter 的子类}
 * @author Li
 * @since 2018/8/26
 */
public abstract class BaseMVPActivity<V extends IBaseView, T extends BasePresenter<V>>
        extends BaseActivity implements IBaseView {

    /**
     * Presenter:{@linkplain BasePresenter 的子类}
     * zai子类中使用
     */
    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView((V) this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    /**
     * 创建一个Presenter,并返回,在{@link #onCreate(Bundle)}方法中赋值给{@link #presenter}
     *
     * @return {@link BasePresenter}
     */
    protected abstract T createPresenter();

    /**
     * 显示加载框
     */
    @Override
    public void showLoading() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    /**
     * 关闭加载框
     */
    @Override
    public void dismissLoading() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

}
