package com.quickvideo.quickvideo.RecommendPackage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.bean.XiangQingBean;

/**
 * Created by Long° Engagement on 2017/12/6.
 */

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.MyXQViewHolder> {
    Context context;
    XiangQingBean xiangQingBean;

    public DetailsAdapter(Context context, XiangQingBean xiangQingBean) {
        this.context = context;
        this.xiangQingBean = xiangQingBean;
    }
    public interface OnItemClieckLinster{

        void onItemClickListener(View view, int pos);

    }
    private OnItemClieckLinster onItemClieckLinster;
    public void setOnItemClieckLinster(OnItemClieckLinster listener){

        this.onItemClieckLinster = listener;
    }
    @Override
    public MyXQViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       MyXQViewHolder adapter = new MyXQViewHolder(LayoutInflater.from(context).inflate(R.layout.intro_item,null));
        return adapter;
    }

    @Override
    public void onBindViewHolder(final MyXQViewHolder holder, final int position) {
       holder.img.setImageURI(xiangQingBean.ret.list.get(0).childList.get(position).pic);
       holder.title.setText(xiangQingBean.ret.list.get(0).childList.get(position).title );
        if (onItemClieckLinster!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClieckLinster.onItemClickListener(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (!xiangQingBean.msg.equals("视频已下线")) {
            return xiangQingBean.ret.list.get(0).childList.size();
        }else{
            return 0;
        }
    }




    public class MyXQViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView img;
        TextView title;
        public MyXQViewHolder(View itemView) {
            super(itemView);
            img= (SimpleDraweeView) itemView.findViewById(R.id.show_img);
            title= (TextView) itemView.findViewById(R.id.show_title);
        }
    }
}
