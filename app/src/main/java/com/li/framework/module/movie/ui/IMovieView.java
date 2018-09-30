package com.li.framework.module.movie.ui;

import com.li.framework.base.view.IBaseView;
import com.li.framework.module.movie.model.bean.MovieSubjects;

import java.util.List;

/**
 * @author li
 * @since 2018/9/29
 */
public interface IMovieView extends IBaseView {

    void showMovies(List<MovieSubjects.Subjects> data);
}
