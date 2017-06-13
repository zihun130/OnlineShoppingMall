package com.atguigu.shoppingmall_1020.adapter.homeadapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.shoppingmall_1020.domain.HomeBean;
import com.atguigu.shoppingmall_1020.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by sun on 2017/6/13.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private final Context context;
    private final List<HomeBean.ResultBean.ActInfoBean> datas;

    public ViewPagerAdapter(Context context, List<HomeBean.ResultBean.ActInfoBean> act_info) {
        this.context=context;
        this.datas=act_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView=new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        HomeBean.ResultBean.ActInfoBean actInfoBean = datas.get(position);
        Glide.with(context).load(Constants.BASE_URL_IMAGE+actInfoBean.getIcon_url()).into(imageView);

        container.addView(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener != null){
                    clickListener.onItemClick(position);
                }
            }
        });


        return imageView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    public interface OnItemClickListener{
        public void onItemClick(int position);
    }

    private OnItemClickListener clickListener;

    /**
     * 设置点击item的监听
     * @param
     */
    public void setOnItemClickListener( OnItemClickListener listener){
        this.clickListener = listener;
    }
}
