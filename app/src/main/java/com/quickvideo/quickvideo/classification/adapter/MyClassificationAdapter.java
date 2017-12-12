package com.quickvideo.quickvideo.classification.adapter;

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
import com.quickvideo.quickvideo.clientutils.OnClickRecyclerListner;

import java.util.List;

/**
 * Created by Administrator on 2017/12/5.
 */

public class MyClassificationAdapter extends XRecyclerView.Adapter<MyClassificationAdapter.ViewHolder>{
    public Context context;
    public List<ShouYeBean.RetBean.ListBean> list;
    int screenWidth;

    public MyClassificationAdapter(Context context, List<ShouYeBean.RetBean.ListBean> list,int screenWidth) {
        this.context = context;
        this.list = list;
        this.screenWidth = screenWidth;
    }

    @Override
    public MyClassificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
//        ViewHolder holder = new ViewHolder(LayoutInflater.from(
//                context).inflate(R.layout.classificationadapteritem, parent, false));
//                 return holder;
        View v = LayoutInflater.from(context).inflate(R.layout.classificationadapteritem, parent, false);
        final ViewHolder holder=new ViewHolder(v);

        return new MyClassificationAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyClassificationAdapter.ViewHolder holder, final int position) {
            holder.tv.setText(list.get(position).title);
            String url = list.get(position).childList.get(0).pic;
             Uri uri = Uri.parse(url);
             holder.img.setImageURI(uri);
        if(listner!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //holder.getLayoutPosition()获取点击的条目位置；
                    listner.onItemClick(view,position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listner.onLongItemClick(view,position);
                    //防止与click事件冲突
                    return true;
                }
            });
        }


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
