package com.li.framework.mvp.ui;

import com.li.framework.mvp.model.bean.News;

/**
 * @author Li
 * @since 2018/8/26
 */
public interface INewsView extends BaseView {

    void showNews(News news);

    void onError(String error);

}
