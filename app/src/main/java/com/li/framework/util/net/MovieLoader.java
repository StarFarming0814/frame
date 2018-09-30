package com.li.framework.util.net;

import com.li.framework.module.movie.model.MovieService;
import com.li.framework.module.movie.model.bean.MovieSubjects;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author li
 * @since 2018/9/29
 */
public class MovieLoader extends ObjectLoader {

    private MovieService service;

    public MovieLoader() {
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
