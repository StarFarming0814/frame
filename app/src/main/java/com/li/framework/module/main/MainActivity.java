package com.li.framework.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.li.framework.R;
import com.li.framework.base.activity.BaseActivity;
import com.li.framework.module.movie.ui.activity.MovieListActivity;
import com.li.framework.news.ui.activity.NewsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {


    @BindView(R.id.main_list)
    ListView main_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        List<MenuBean> menus = new ArrayList<>();
        menus.add(new MenuBean("聚合新闻", 0));
        menus.add(new MenuBean("豆瓣电影", 1));
        MainAdapter adapter = new MainAdapter(this, menus);
        main_list.setAdapter(adapter);
        main_list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getAdapter() instanceof MainAdapter) {
            MainAdapter adapter = (MainAdapter) parent.getAdapter();
            switch (adapter.getItem(position).getId()) {
                case 0:
                    startActivity(new Intent(this, NewsActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(this, MovieListActivity.class));
                    break;
            }
        }
    }
}
