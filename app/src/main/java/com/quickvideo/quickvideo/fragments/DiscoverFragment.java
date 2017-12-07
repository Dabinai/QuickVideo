package com.quickvideo.quickvideo.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.quickvideo.quickvideo.R;
import com.quickvideo.quickvideo.bean.PinDaoBean;
import com.quickvideo.quickvideo.client.ApiService;
import com.quickvideo.quickvideo.client.ClientUtils;
import com.quickvideo.quickvideo.discoverall.DiscoverAdapterHua;
import com.quickvideo.quickvideo.discoverall.cardswipelayout.CardConfig;
import com.quickvideo.quickvideo.discoverall.cardswipelayout.CardItemTouchHelperCallback;
import com.quickvideo.quickvideo.discoverall.cardswipelayout.CardLayoutManager;
import com.quickvideo.quickvideo.discoverall.cardswipelayout.OnSwipeListener;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Dabin on 2017/12/4.
 * 发现：DiscoverFragment
 * 仿探探卡片滑动+换一批（分页换数据），
 * 点击事件（点击跳到VideoInfoActivity）
 */

public class DiscoverFragment extends Fragment {

    @BindView(R.id.discover_button)
    Button discoverButton;
    Unbinder unbinder;
    private View view;
    private RecyclerView recyclerView;
    private int page = 1;
    private DiscoverAdapterHua discoverAdapterHua;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.frag_discover, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = view.findViewById(R.id.discover_recycler);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();

    }

    public void initData() {
        ApiService apiService = ClientUtils.getDefault().create(ApiService.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("catalogId", "402834815584e463015584e539330016");
        map.put("pnum", page + "");
        final Observable<PinDaoBean> pinDaoData = apiService.getPinDaoData(map);
        pinDaoData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PinDaoBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(PinDaoBean pinDaoBean) {
                        final List<PinDaoBean.RetBean.ListBean> list = pinDaoBean.ret.list;
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        discoverAdapterHua = new DiscoverAdapterHua(getActivity(), pinDaoBean.ret.list);
                        recyclerView.setAdapter(discoverAdapterHua);
                        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerView.getAdapter(), list);
                        cardCallback.setOnSwipedListener(new OnSwipeListener<PinDaoBean.RetBean.ListBean>() {
                            @Override
                            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                                DiscoverAdapterHua.MyHolder myHolder = (DiscoverAdapterHua.MyHolder) viewHolder;
                                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
                                if (direction == CardConfig.SWIPING_LEFT) {
                                    myHolder.dislikeImageView.setAlpha(Math.abs(ratio));
                                } else if (direction == CardConfig.SWIPING_RIGHT) {
                                    myHolder.likeImageView.setAlpha(Math.abs(ratio));
                                } else {
                                    myHolder.dislikeImageView.setAlpha(0f);
                                    myHolder.likeImageView.setAlpha(0f);
                                }
                            }
                            @Override
                            public void onSwiped(RecyclerView.ViewHolder viewHolder, PinDaoBean.RetBean.ListBean mybean, int direction) {
                                DiscoverAdapterHua.MyHolder myHolder = (DiscoverAdapterHua.MyHolder) viewHolder;
                                viewHolder.itemView.setAlpha(1f);
                                myHolder.dislikeImageView.setAlpha(0f);
                                myHolder.likeImageView.setAlpha(0f);
                            }
                            @Override
                            public void onSwipedClear() {
                                Toast.makeText(getActivity(), "data clear", Toast.LENGTH_SHORT).show();
                                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                                progressDialog.setTitle("等待");
                                progressDialog.setMessage("人家正在加载呢！");
                                progressDialog.show();

                                recyclerView.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        page++;
                                        initData();
                                        progressDialog.dismiss();
                                        recyclerView.getAdapter().notifyDataSetChanged();
                                    }
                                }, 3000L);
                            }
                        });
                        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
                        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
                        recyclerView.setLayoutManager(cardLayoutManager);
                        touchHelper.attachToRecyclerView(recyclerView);
                        discoverAdapterHua.setOnItemClickLitener(new DiscoverAdapterHua.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Toast.makeText(getActivity(), ""+list.get(position).title, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.discover_button)
    public void onViewClicked() {
        page++;
        initData();
    }

}
