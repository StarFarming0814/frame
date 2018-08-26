package com.li.framework.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author Li
 * @since 2018/8/26
 */
public abstract class BaseListAdapter<E> extends BaseAdapter {

    protected Context context;
    protected List<E> data;
    protected LayoutInflater inflater;

    public BaseListAdapter(Context context, List<E> data) {
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public E getItem(int position) {
        return data == null ? null : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent, getItem(position));
    }

    public abstract View getView(int position, View convertView, ViewGroup parent, E bean);
}
