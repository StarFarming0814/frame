package com.li.framework.module.movie.model;


import com.li.framework.module.movie.model.bean.MovieSubjects;

import rx.Subscriber;

/**
 * @author li
 * @since 2018/9/29
 */
public interface IMovieModel<T> {
//    void loadMovies(T callBack);

    void loadMoviesRxJava(T subscription);

    void cancel();
}
