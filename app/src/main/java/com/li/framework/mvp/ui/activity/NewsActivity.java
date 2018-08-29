package com.li.framework.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.li.framework.R;
import com.li.framework.WebActivity;
import com.li.framework.base.activity.BaseMVPActivity;
import com.li.framework.mvp.model.bean.News;
import com.li.framework.mvp.presenter.impl.NewsPresenter;
import com.li.framework.mvp.ui.INewsView;
import com.li.framework.mvp.ui.adapter.NewsAdapter;

/**
 * @author Li
 * @since 2018/8/26
 */
public class NewsActivity extends BaseMVPActivity<INewsView, NewsPresenter> implements INewsView {

    private ListView list;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        list = findViewById(R.id.list);

        if (presenter != null) {
            presenter.loadPage();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.cancel();
        }
    }

    @Override
    public void showNews(News news) {
        adapter = new NewsAdapter(this, news.getResult().getData());
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(NewsActivity.this, WebActivity.class)
                        .putExtra("url", adapter.getItem(i).getUrl()));
            }
        });
    }

    @Override
    public void onError(String error) {
        toast(error);
    }


    /**
     * 创建Presenter
     *
     * @return {@link NewsPresenter}
     */
    @Override
    protected NewsPresenter createPresenter() {
        return new NewsPresenter();
    }
}
