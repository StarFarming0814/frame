package com.li.framework.module.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.li.framework.R;
import com.li.framework.base.adapter.BaseListAdapter;

import java.util.List;

/**
 * @author li
 * @since 2018/9/29
 */
public class MainAdapter extends BaseListAdapter<MenuBean> {


    public MainAdapter(Context context, List<MenuBean> data) {
        super(context, data);
    }

    @Override
    @SuppressLint("InflateParams")
    public View getView(int position, View convertView, ViewGroup parent, MenuBean bean) {
        TextView item_txt;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_main, null);
            item_txt = convertView.findViewById(R.id.item_txt);
            convertView.setTag(item_txt);
        } else {
            item_txt = (TextView) convertView.getTag();
        }
        item_txt.setText(bean.getTitle());
        return convertView;
    }


}
