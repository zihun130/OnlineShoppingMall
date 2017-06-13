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
 * Created by sun on 2017/6/13.
 */

public class ChannelGridAdapter extends BaseAdapter {
    private final Context context;
    private final List<HomeBean.ResultBean.ChannelInfoBean> datas;

    public ChannelGridAdapter(Context context, List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {
        this.context = context;
        this.datas = channel_info;
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
            convertView = View.inflate(context, R.layout.item_channel, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        HomeBean.ResultBean.ChannelInfoBean infoBean = datas.get(position);
        viewHolder.tvChannel.setText(infoBean.getChannel_name());

        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE+infoBean.getImage())
                .into(viewHolder.ivChannel);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_channel)
        ImageView ivChannel;
        @InjectView(R.id.tv_channel)
        TextView tvChannel;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
