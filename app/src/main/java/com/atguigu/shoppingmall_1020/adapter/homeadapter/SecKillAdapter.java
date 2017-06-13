package com.atguigu.shoppingmall_1020.adapter.homeadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.shoppingmall_1020.R;
import com.atguigu.shoppingmall_1020.domain.HomeBean;
import com.atguigu.shoppingmall_1020.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/6/13.
 */

public class SecKillAdapter extends RecyclerView.Adapter<SecKillAdapter.MyViewHolder> {
    private final Context context;
    private final List<HomeBean.ResultBean.SeckillInfoBean.ListBean> datas;


    @Override
    public int getItemCount() {
        return datas.size();
    }

    public SecKillAdapter(Context context, HomeBean.ResultBean.SeckillInfoBean seckill_info) {
        this.context = context;
        this.datas = seckill_info.getList();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_seckill, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HomeBean.ResultBean.SeckillInfoBean.ListBean listBean = datas.get(position);
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + listBean.getFigure())
                .into(holder.ivFigure);
        holder.tvCoverPrice.setText("￥" + listBean.getCover_price());
        holder.tvOriginPrice.setText("￥" + listBean.getOrigin_price());

    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.iv_figure)
        ImageView ivFigure;
        @InjectView(R.id.tv_cover_price)
        TextView tvCoverPrice;
        @InjectView(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @InjectView(R.id.ll_root)
        LinearLayout llRoot;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.OnItemClick(getLayoutPosition());
                    }

                }
            });
        }
    }


    public interface OnItemClickListener {
        public void OnItemClick(int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
