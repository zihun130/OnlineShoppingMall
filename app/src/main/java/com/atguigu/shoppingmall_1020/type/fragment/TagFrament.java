package com.atguigu.shoppingmall_1020.type.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.shoppingmall_1020.base.BaseFragment;

/**
 * Created by sun on 2017/6/16.
 */

public class TagFrament extends BaseFragment{
    private TextView textView;
    @Override
    public View initView() {
        textView=new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setTextSize(50);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("标签的页面");
    }
}
