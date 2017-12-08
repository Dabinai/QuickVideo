package com.quickvideo.quickvideo.mainui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.bean.MenuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xue on 2017-12-07.
 * menulist适配器
 */

public class MenusAdapter extends BaseAdapter{

    Context mcontext;
    ArrayList<MenuBean> list;


    public MenusAdapter(Context mcontext, ArrayList<MenuBean> list) {
        this.mcontext = mcontext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.menu_list_item, null);
            holder.item_name=convertView.findViewById(R.id.menu_item_name);
            holder.item_icon=convertView.findViewById(R.id.menu_item_icon);
            convertView.setTag(holder);

        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        MenuBean menuBean = list.get(i);

        holder.item_name.setText(menuBean.getMenu_item_name());
        holder.item_icon.setImageResource(menuBean.getMenu_item_icon());

        return convertView;
    }
    public class ViewHolder{
        TextView item_name;
        ImageView item_icon;
    }
}
