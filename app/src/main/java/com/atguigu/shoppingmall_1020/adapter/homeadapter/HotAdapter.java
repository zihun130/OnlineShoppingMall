package com.atguigu.shoppingmall_1020.adapter.homeadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shoppingmall_1020.R;
import com.atguigu.shoppingmall_1020.domain.HomeBean;
import com.atguigu.shoppingmall_1020.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/6/14.
 */

public class HotAdapter extends BaseAdapter {
    private final Context context;
    private final List<HomeBean.ResultBean.HotInfoBean> datas;

    public HotAdapter(Context context, List<HomeBean.ResultBean.HotInfoBean> hot_info) {
        this.context = context;
        this.datas = hot_info;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_hotgrid, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        HomeBean.ResultBean.HotInfoBean hotInfoBean = datas.get(position);
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE+hotInfoBean.getFigure())
                .into(viewHolder.ivHot);
        viewHolder.tvName.setText(hotInfoBean.getName());
        viewHolder.tvPrice.setText("￥" + hotInfoBean.getCover_price());
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_hot)
        ImageView ivHot;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
