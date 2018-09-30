package com.li.framework.news.ui;

import com.li.framework.base.view.IBaseView;
import com.li.framework.news.model.bean.News;

/**
 * @author Li
 * @since 2018/8/26
 */
public interface INewsView extends IBaseView {

    /**
     * 在界面上显示数据
     *
     * @param news {@link News}
     */
    void showNews(News news);

    void onError(String error);

}
