package com.quickvideo.quickvideo.serchaall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.quickvideo.quickvideo.R;

import java.util.List;

/**
 * Created by Administrator on 2017/12/7.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private  List<SearchBean.RetBean.ListBean> mDatas;
    private Context mContext;
    private LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;

    public MyRecyclerAdapter(Context context, List<SearchBean.RetBean.ListBean> datas){
        this. mContext=context;
        this. mDatas=datas;
        inflater=LayoutInflater. from(mContext);
    }
    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }
    @Override
    public int getItemCount() {

        return mDatas.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
  if (mOnItemClickListener!= null){
      holder.itemView.setOnClickListener( new View.OnClickListener() {

          @Override
          public void onClick(View v) {
              mOnItemClickListener.onClick(position);
          }
      });

      holder. itemView.setOnLongClickListener( new View.OnLongClickListener() {
          @Override
          public boolean onLongClick(View v) {
              mOnItemClickListener.onLongClick(position);
              return false;
          }
      });

  }
        holder.item_title.setText(mDatas.get(position).getTitle());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(mDatas.get(position).getPic())
                .setAutoPlayAnimations(true)
                .build();
        holder.sdv.setController(controller);

        }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_view,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView item_title;
        SimpleDraweeView sdv;
        public MyViewHolder(View view) {
            super(view);
            item_title=(TextView) view.findViewById(R.id.item_title);
             sdv=view.findViewById(R.id.sdv);
        }

    }
}