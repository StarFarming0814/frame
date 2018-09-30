package com.li.framework.news.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.li.framework.R;
import com.li.framework.base.adapter.BaseListAdapter;
import com.li.framework.news.model.bean.News.Result.Data;

import java.util.List;

/**
 * @author Li
 * @since 2018/8/26
 */
public class NewsAdapter extends BaseListAdapter<Data> {

    public NewsAdapter(Context context, List<Data> data) {
        super(context, data);
    }

    @Override
    @SuppressLint("InflateParams")
    public View getView(int position, View convertView, ViewGroup parent, Data bean) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_news, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(bean.getTitle());
        holder.author.setText(String.format("作者：%s", bean.getAuthor_name()));
        holder.date.setText(bean.getDate());
        return convertView;
    }

    class ViewHolder {
        TextView title;
        TextView author;
        TextView date;

        ViewHolder(View itemView) {
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            date = itemView.findViewById(R.id.date);
        }
    }
}
