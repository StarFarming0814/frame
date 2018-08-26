package com.li.framework.base.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.li.framework.mvp.presenter.BasePresenter;
import com.li.framework.mvp.ui.BaseView;

/**
 * @author Li
 * @since 2018/8/26
 */
public abstract class BaseActivity<V extends BaseView, T extends BasePresenter<V>>
        extends AppCompatActivity implements BaseView {

    protected T presenter;
    private Toast toast;

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

    protected abstract T createPresenter();


    /**
     * 显示加载框
     */
    @Override
    public void showLoading() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void dismissLoading() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @SuppressLint("ShowToast")
    protected void toast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    @SuppressLint("ShowToast")
    protected void toast(int stringRes) {
        if (toast == null) {
            toast = Toast.makeText(getApplication(), stringRes, Toast.LENGTH_SHORT);
        } else {
            toast.setText(stringRes);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
