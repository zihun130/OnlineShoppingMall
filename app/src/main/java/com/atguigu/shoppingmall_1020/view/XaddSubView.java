package com.atguigu.shoppingmall_1020.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.shoppingmall_1020.R;

/**
 * Created by sun on 2017/6/14.
 */

public class XaddSubView extends LinearLayout implements View.OnClickListener {

    private Context context;
    private ImageView iv_sub;
    private TextView  tv_value;
    private ImageView iv_add;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tv_value.setText(value+"");
    }

    public int getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(int maxvalue) {
        this.maxvalue = maxvalue;
    }

    public int getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(int minvalue) {
        this.minvalue = minvalue;
    }

    private int value=1;
    private int minvalue=1;
    private int maxvalue=10;

    public XaddSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
       View.inflate(context, R.layout.add_sub_view,XaddSubView.this);
        iv_sub = (ImageView)findViewById(R.id.iv_sub);
        tv_value = (TextView)findViewById(R.id.tv_value);
        iv_add = (ImageView)findViewById(R.id.iv_add);

        iv_add.setOnClickListener(this);
        iv_sub.setOnClickListener(this);


       //在values中自定义view的属性
        if (attrs != null) {
            //取出属性
            TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.XaddSubView);
            int value = tintTypedArray.getInt(R.styleable.XaddSubView_value, 0);
            if (value > 0) {
                setValue(value);
            }
            int minValue = tintTypedArray.getInt(R.styleable.XaddSubView_minvalue, 0);
            if (value > 0) {
                setMinvalue(minValue);
            }
            int maxValue = tintTypedArray.getInt(R.styleable.XaddSubView_maxvalue, 0);
            if (value > 0) {
                setMaxvalue(maxValue);
            }
            Drawable addDrawable = tintTypedArray.getDrawable(R.styleable.XaddSubView_numberAddBackground);
            if (addDrawable != null) {
                iv_add.setImageDrawable(addDrawable);
            }
            Drawable subDrawable = tintTypedArray.getDrawable(R.styleable.XaddSubView_numberSubBackground);
            if (subDrawable != null) {
                iv_sub.setImageDrawable(subDrawable);
            }
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add :
                addNumber();
                break;
            case R.id.iv_sub :
                subNumber();
                break;

        }
    }

    private void addNumber() {
        if(value<maxvalue){
            value++;
        }
        setValue(value);
        if(listener != null){
            listener.numberChange(value);
        }

    }

    private void subNumber() {
        if(value>minvalue){
            value--;
        }
        setValue(value);
        if(listener != null){
            listener.numberChange(value);
        }

    }

    public interface OnNumberChangerListener{
        public void numberChange(int value);
    }

    private OnNumberChangerListener listener;

    /**
     * 设置数字变化的监听
     * @param l
     */
    public void setOnNumberChangeListener(OnNumberChangerListener l){//l是MyOnNumberChangeListener的实例
        this.listener = l;
    }
}
