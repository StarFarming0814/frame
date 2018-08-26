package com.li.framework.mvp.presenter.impl;

import com.li.framework.mvp.model.bean.News;
import com.li.framework.mvp.model.impl.NewsModel;
import com.li.framework.mvp.presenter.BasePresenter;
import com.li.framework.mvp.ui.INewsView;
import com.li.net.callback.impl.StringCallBack;

/**
 * @author Li
 * @since 2018/8/26
 */
public class NewsPresenter extends BasePresenter<INewsView> {
    private NewsModel model = new NewsModel();

    public void loadPage() {
        if (weakReference.get() != null) {
            weakReference.get().showLoading();
            model.loadNews(new StringCallBack<News>() {
                @Override
                public void onSuccess(News news) {
                    if (weakReference.get() != null) {
                        weakReference.get().showNews(news);
                    }
                }

                @Override
                public void onFailed(String error) {
                    if (weakReference.get() != null) {
                        weakReference.get().onError(error);
                    }
                }

                @Override
                public void onFinished() {
                    if (weakReference.get() != null) {
                        weakReference.get().dismissLoading();
                    }
                }
            });
        }
    }

    public void cancel() {
        model.cancel();
    }
}
