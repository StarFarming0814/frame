package com.li.framework.module.movie.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.li.framework.R;
import com.li.framework.WebActivity;
import com.li.framework.base.activity.BaseMVPActivity;
import com.li.framework.module.movie.model.bean.MovieSubjects;
import com.li.framework.module.movie.presenter.MoviePresenter;
import com.li.framework.module.movie.ui.IMovieView;
import com.li.framework.module.movie.ui.MovieAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author li
 * @since 2018/9/29
 */
public class MovieListActivity extends BaseMVPActivity<IMovieView, MoviePresenter>
        implements IMovieView, AdapterView.OnItemClickListener {

    @BindView(R.id.list)
    ListView list;
    private MovieAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        ButterKnife.bind(this);

        presenter.loadPage();
    }

    @Override
    protected MoviePresenter createPresenter() {
        return new MoviePresenter();
    }

    @Override
    public void showMovies(List<MovieSubjects.Subjects> data) {
        if (data != null) {
            if (adapter == null) {
                adapter = new MovieAdapter(this, data);
                list.setAdapter(adapter);
                list.setOnItemClickListener(this);
            } else {
                adapter.setData(data);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (adapter != null) {
            startActivity(new Intent(this, WebActivity.class)
                    .putExtra("url", adapter.getItem(position).getAlt()));
        }
    }
}
