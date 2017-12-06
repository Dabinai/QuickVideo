package com.quickvideo.quickvideo.Classification.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.bean.ShouYeBean;
import com.quickvideo.quickvideo.client.OnClickRecyclerListner;

import java.util.List;

/**
 * Created by Administrator on 2017/12/5.
 */

public class MyClassificationAdapter extends XRecyclerView.Adapter<MyClassificationAdapter.ViewHolder>{
    public Context context;
    public List<ShouYeBean.RetBean.ListBean> list;

    public MyClassificationAdapter(Context context, List<ShouYeBean.RetBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyClassificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        ViewHolder holder = new ViewHolder(LayoutInflater.from(
//                context).inflate(R.layout.classificationadapteritem, parent, false));
//                 return holder;
        View v = LayoutInflater.from(context).inflate(R.layout.classificationadapteritem, parent, false);
        final ViewHolder holder=new ViewHolder(v);
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
        return new MyClassificationAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyClassificationAdapter.ViewHolder holder, int position) {
            holder.tv.setText(list.get(position).title);
            String url = list.get(position).childList.get(0).pic;
             Uri uri = Uri.parse(url);
             holder.img.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    //点击事件；
    private OnClickRecyclerListner listner;
    //设置点击事件；
    public void setLisner(OnClickRecyclerListner lisner){
        this.listner=lisner;
    }
    class ViewHolder extends XRecyclerView.ViewHolder{
                public TextView tv;
                public SimpleDraweeView img;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.class_tv);
            img = itemView.findViewById(R.id.class_img);
        }
    }
}