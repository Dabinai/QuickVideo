package com.quickvideo.quickvideo.discover;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.bean.PinDaoBean;

import java.util.List;

/**
 * Created by Dabin on 2017/12/7.
 */

public class DiscoverAdapterHua extends XRecyclerView.Adapter {
    private Context mContext;
    private List<PinDaoBean.RetBean.ListBean> list;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public DiscoverAdapterHua(Context mContext, List<PinDaoBean.RetBean.ListBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext,R.layout.discover_huaitem,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyHolder myHolder = (MyHolder) holder;
        myHolder.description.setText(list.get(position).description);
        myHolder.mTextView.setText(list.get(position).title);
        Glide.with(mContext)
                .load(list.get(position).pic)
                .placeholder(R.drawable.loading_01)
                .crossFade()
                .into(myHolder.img);
        if(mOnItemClickLitener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(view,position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        ImageView img;
        TextView description;
        public ImageView dislikeImageView;
        public ImageView likeImageView;

        public MyHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.titless);
            img = itemView.findViewById(R.id.imggg);
            description = itemView.findViewById(R.id.description);
            dislikeImageView=itemView.findViewById(R.id.iv_dislike);
            likeImageView=itemView.findViewById(R.id.iv_like);
        }


    }
}
