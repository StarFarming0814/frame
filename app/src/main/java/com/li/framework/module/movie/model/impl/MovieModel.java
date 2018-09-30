package com.li.framework.module.movie.model.impl;

import com.li.framework.module.movie.model.IMovieModel;
import com.li.framework.module.movie.model.MovieService;
import com.li.framework.module.movie.model.bean.MovieSubjects;
import com.li.framework.util.net.ObjectLoader;
import com.li.framework.util.net.RetrofitServiceManager;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author li
 * @since 2018/9/29
 */
public class MovieModel extends ObjectLoader implements IMovieModel<Subscriber<MovieSubjects>> {

    private static final String BASE_URL = "https://api.douban.com/v2/movie/";

    @Override
    public void loadMoviesRxJava(Subscriber<MovieSubjects> subscriber) {
        MovieService service = RetrofitServiceManager.getInstance().create(MovieService.class);
        service.getTop250s(0, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void cancel() {

    }


    private MovieService service;

    public MovieModel() {
        service = RetrofitServiceManager.getInstance().create(MovieService.class);
    }

    public Observable<List<MovieSubjects.Subjects>> getMovie(int start, int count) {
        return observe(service.getTop250s(start, count))
                .map(new Func1<MovieSubjects, List<MovieSubjects.Subjects>>() {
                    @Override
                    public List<MovieSubjects.Subjects> call(MovieSubjects movieSubjects) {
                        return movieSubjects.getSubjects();
                    }
                });
    }

    private Observable<String> getWeatherList(String cityId, String key) {
        return observe(service.getWeather(cityId, key))
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s;
                    }
                });
    }
}
