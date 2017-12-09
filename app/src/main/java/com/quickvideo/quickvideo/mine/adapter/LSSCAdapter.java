package com.quickvideo.quickvideo.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.mine.bean.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/12/9.
 */

public class LSSCAdapter extends BaseAdapter {
    private Context context;
    private List<Bean> list = new ArrayList<>();

    public LSSCAdapter(Context context, List<Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_grid_layout, null);
            holder = new ViewHolder();
            holder.img = convertView.findViewById(R.id.grid_fresco);
            holder.name = convertView.findViewById(R.id.grid_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getPic()).into(holder.img);
        return convertView;
    }

    class ViewHolder {
        TextView name;
        ImageView img;
    }
}
