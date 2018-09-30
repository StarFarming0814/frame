package com.li.framework.module.movie.presenter;

import com.li.framework.base.presenter.BasePresenter;
import com.li.framework.module.movie.model.bean.MovieSubjects;
import com.li.framework.module.movie.model.impl.MovieModel;
import com.li.framework.module.movie.ui.IMovieView;

import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author li
 * @since 2018/9/29
 */
public class MoviePresenter extends BasePresenter<IMovieView> {

    private MovieModel movieModel = new MovieModel();

    public void loadPage() {
        if (weakReference.get() != null) {

            weakReference.get().showLoading();
            movieModel.getMovie(0, 20).subscribe(new Action1<List<MovieSubjects.Subjects>>() {
                @Override
                public void call(List<MovieSubjects.Subjects> subjects) {
                    if (weakReference.get() != null) {
                        weakReference.get().showMovies(subjects);
                        weakReference.get().dismissLoading();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    if (weakReference.get() != null) {
                        weakReference.get().toast(throwable.getMessage());
                        weakReference.get().dismissLoading();
                    }
                }
            });
        }
    }

}
