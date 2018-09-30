package com.li.framework.module.movie.model;


import com.li.framework.module.movie.model.bean.MovieSubjects;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author li
 * @since 2018/9/29
 */
public interface MovieService {

    @GET("top250")
    Observable<MovieSubjects> getTop250s(@Query("starts") int start, @Query("count") int count);

    @FormUrlEncoded
    @POST("/x3/weather")
    Observable<String> getWeather(@Field("cityId") String cityId, @Field("key") String key);
}
