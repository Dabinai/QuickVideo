package com.quickvideo.quickvideo.mine.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.mine.bean.MineBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by powersen on 2017/12/7.
 */

public class MineGridAdapter extends BaseAdapter {
    private Context context;
    private List<MineBean> list = new ArrayList<>();

    public MineGridAdapter(Context context, List<MineBean> list) {
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
            holder.name = convertView.findViewById(R.id.grid_name);
            holder.img = convertView.findViewById(R.id.grid_fresco);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.img.setBackgroundResource(list.get(position).getIcon());
        holder.name.setText(list.get(position).getName());
        return convertView;
    }

    class ViewHolder {
        public ImageView img;
        public TextView name;

    }
}
