package com.quickvideo.quickvideo.RecommendPackage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.bean.PingLunBean;

/**
 * Created by Long° Engagement on 2017/12/6.
 */

public class PLAdapter extends RecyclerView.Adapter<PLAdapter.MyViewHolder>{
    Context context;
    PingLunBean pingLunBean;

    public PLAdapter(Context context, PingLunBean pingLunBean) {
        this.context = context;
        this.pingLunBean = pingLunBean;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.comment_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.img.setImageURI(pingLunBean.ret.list.get(position).userPic);
        holder.name.setText(pingLunBean.ret.list.get(position).phoneNumber);
        holder.connect.setText(pingLunBean.ret.list.get(position).msg);
        holder.time.setText(pingLunBean.ret.list.get(position).time);

    }

    @Override
    public int getItemCount() {
        return pingLunBean.ret.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView img;
        TextView name,connect,time;
        public MyViewHolder(View itemView) {
            super(itemView);
            img= (SimpleDraweeView) itemView.findViewById(R.id.datil_img);
            name= (TextView) itemView.findViewById(R.id.name);
            connect= (TextView) itemView.findViewById(R.id.connect);
            time = itemView.findViewById(R.id.time);
        }
    }
}
