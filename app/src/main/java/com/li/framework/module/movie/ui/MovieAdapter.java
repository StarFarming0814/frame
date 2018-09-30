package com.li.framework.module.movie.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.li.framework.R;
import com.li.framework.base.adapter.BaseListAdapter;
import com.li.framework.module.movie.model.bean.MovieSubjects;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author li
 * @since 2018/9/29
 */
public class MovieAdapter extends BaseListAdapter<MovieSubjects.Subjects> {

    public MovieAdapter(Context context, List<MovieSubjects.Subjects> data) {
        super(context, data);
    }

    @Override
    @SuppressLint("InflateParams")
    public View getView(int position, View convertView, ViewGroup parent, MovieSubjects.Subjects bean) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_movie, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context)
                .load(bean.getImages().getMedium())
                .into(holder.movie_img);

        holder.movie_title.setText(String.format("%s/%s", bean.getTitle(), bean.getOriginal_title()));
        holder.movie_rating.setText(String.format("豆瓣评分:%s", bean.getRating().getAverage()));
        return convertView;
    }

    class ViewHolder {

        @BindView(R.id.movie_img)
        ImageView movie_img;
        @BindView(R.id.movie_title)
        TextView movie_title;
        @BindView(R.id.movie_rating)
        TextView movie_rating;

        ViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
