package com.li.framework.news.model;

import com.li.net.callback.ICallBack;

/**
 * @author Li
 * @since 2018/8/26
 */
public interface INewsModel<T extends ICallBack> {
    void loadNews(T listener);
    void cancel();
}
