package com.quickvideo.quickvideo.VideoList.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quickvideo.quickvideo.Classification.adapter.MyClassificationAdapter;
import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.bean.PinDaoBean;
import com.quickvideo.quickvideo.client.OnClickRecyclerListner;

import java.util.List;

/**
 * Created by Administrator on 2017/12/6.
 */

public class MyVideoListAdapter extends XRecyclerView.Adapter<MyVideoListAdapter.ViewHolder> {
    public Context context;
    public List<PinDaoBean.RetBean.ListBean> vlist;

    public MyVideoListAdapter(Context context, List<PinDaoBean.RetBean.ListBean> vlist) {
        this.context = context;
        this.vlist = vlist;
    }

    @Override
    public MyVideoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.videolistitem, parent, false);
        final MyVideoListAdapter.ViewHolder holder=new MyVideoListAdapter.ViewHolder(v);
        if(listner!=null){
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //holder.getLayoutPosition()获取点击的条目位置；
                    listner.onItemClick(view,holder.getLayoutPosition());
                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listner.onLongItemClick(view,holder.getLayoutPosition());
                    //防止与click事件冲突
                    return true;
                }
            });
        }
        return new MyVideoListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyVideoListAdapter.ViewHolder holder, int position) {

                Glide.with(context).load(vlist.get(position).pic).into(holder.videolistitem_img);
                holder.videolistitem_tv.setText(vlist.get(position).title);
    }

    @Override
    public int getItemCount() {
        return vlist.size();
    }
    //点击事件；
    private OnClickRecyclerListner listner;
    //设置点击事件；
    public void setLisner(OnClickRecyclerListner lisner){
        this.listner=lisner;
    }

    public class ViewHolder extends XRecyclerView.ViewHolder{

                public ImageView videolistitem_img;
                public TextView videolistitem_tv;
        public ViewHolder(View itemView) {
            super(itemView);

            videolistitem_img = itemView.findViewById(R.id.videolistitem_img);
            videolistitem_tv = itemView.findViewById(R.id.videolistitem_tv);
        }
    }
}
