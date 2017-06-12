package atguigu.com.onlineshoppingmall.find;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import atguigu.com.onlineshoppingmall.base.BaseFragment;

/**
 * Created by sun on 2017/6/11.
 */

public class FindPagerFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initview() {
        textView=new TextView(context);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initdata() {
        super.initdata();
        textView.setText("发现页面");
    }
}
