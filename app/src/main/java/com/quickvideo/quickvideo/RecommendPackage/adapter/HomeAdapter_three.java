package com.quickvideo.quickvideo.RecommendPackage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.bean.ShouYeBean;
import com.quickvideo.quickvideo.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long° Engagement on 2017/12/5.
 */

public class HomeAdapter_three extends RecyclerView.Adapter<HomeAdapter_three.MyThreeAdapter> {
    List<ShouYeBean.RetBean.ListBean.ChildListBean> childList = new ArrayList<>();
    Context context;
    int screenWidth;

    public HomeAdapter_three(List<ShouYeBean.RetBean.ListBean.ChildListBean> childList, Context context, int screenWidth) {
        this.childList = childList;
        this.context = context;
        this.screenWidth = screenWidth;
    }

    public interface OnItemClieckLinster{
        void onItemClickListener(View view, int pos);

    }
    private OnItemClieckLinster onItemClieckLinster;
    public void setOnItemClieckLinster(OnItemClieckLinster listener){

        this.onItemClieckLinster = listener;
    }

    @Override
    public MyThreeAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        MyThreeAdapter hodler = new MyThreeAdapter(LayoutInflater.from(context).inflate(R.layout.recommend_3_1,null));
        return hodler;
    }

    @Override
    public void onBindViewHolder(final MyThreeAdapter holder, final int position) {
        holder.img.setImageURI(childList.get(position).pic);

        //由于需要实现瀑布流的效果,所以就需要动态的改变控件的高度了
        ViewGroup.LayoutParams params =holder.img.getLayoutParams();
        params.width=screenWidth;
        holder.img.setLayoutParams(params);

        holder.title.setText(childList.get(position).title);
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
        return childList.size();
    }

    public class MyThreeAdapter extends RecyclerView.ViewHolder {
        SimpleDraweeView img;
        TextView title;
        public MyThreeAdapter(View itemView) {
            super(itemView);
            img= (SimpleDraweeView) itemView.findViewById(R.id.home_img);
            title= (TextView) itemView.findViewById(R.id.home_title);
        }
    }
}
