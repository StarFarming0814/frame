package com.li.framework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.li.framework.base.activity.BaseActivity;
import com.li.framework.mvp.ui.activity.NewsActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        startActivity(new Intent(this, NewsActivity.class));
    }
}
