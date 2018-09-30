package com.li.framework.news.model.impl;

import com.li.framework.base.C;
import com.li.framework.news.model.INewsModel;
import com.li.framework.news.model.bean.News;
import com.li.net.HttpUtils;
import com.li.net.callback.impl.StringCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Li
 * @since 2018/8/26
 */
public class NewsModel implements INewsModel<StringCallBack<News>> {

    @Override
    public void loadNews(StringCallBack<News> listener) {
        String url = C.url.URL_NEWS;
        Map<String, String> map = new HashMap<>();
        map.put("type", "top");
        map.put("key", C.key.KEY_JU_HE_NEWS);
        HttpUtils.getInstance().post(url, map, this, listener);
    }

    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }
}
