package com.li.framework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebView;

import com.li.framework.base.activity.BaseActivity;
import com.li.framework.mvp.presenter.BasePresenter;

/**
 * @author Li
 * @since 2018/8/26
 */
public class WebActivity extends BaseActivity {

    private WebView web;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        web = findViewById(R.id.web);
        String url = getIntent().getStringExtra("url");
        web.loadUrl(url);
        Log.d("aaa", url);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
