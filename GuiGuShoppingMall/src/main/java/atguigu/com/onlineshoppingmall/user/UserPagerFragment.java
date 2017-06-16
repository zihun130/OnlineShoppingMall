package atguigu.com.onlineshoppingmall.user;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hankkin.gradationscroll.GradationScrollView;

import atguigu.com.onlineshoppingmall.R;
import atguigu.com.onlineshoppingmall.base.BaseFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by sun on 2017/6/11.
 */

public class UserPagerFragment extends BaseFragment implements GradationScrollView.ScrollViewListener{
    @InjectView(R.id.ib_user_icon_avator)
    ImageButton ibUserIconAvator;
    @InjectView(R.id.tv_username)
    TextView tvUsername;
    @InjectView(R.id.rl_header)
    RelativeLayout rlHeader;
    @InjectView(R.id.tv_all_order)
    TextView tvAllOrder;
    @InjectView(R.id.tv_user_pay)
    TextView tvUserPay;
    @InjectView(R.id.tv_user_receive)
    TextView tvUserReceive;
    @InjectView(R.id.tv_user_finish)
    TextView tvUserFinish;
    @InjectView(R.id.tv_user_drawback)
    TextView tvUserDrawback;
    @InjectView(R.id.tv_user_location)
    TextView tvUserLocation;
    @InjectView(R.id.tv_user_collect)
    TextView tvUserCollect;
    @InjectView(R.id.tv_user_coupon)
    TextView tvUserCoupon;
    @InjectView(R.id.tv_user_score)
    TextView tvUserScore;
    @InjectView(R.id.tv_user_prize)
    TextView tvUserPrize;
    @InjectView(R.id.tv_user_ticket)
    TextView tvUserTicket;
    @InjectView(R.id.tv_user_invitation)
    TextView tvUserInvitation;
    @InjectView(R.id.tv_user_callcenter)
    TextView tvUserCallcenter;
    @InjectView(R.id.tv_user_feedback)
    TextView tvUserFeedback;
    @InjectView(R.id.ll_root)
    LinearLayout llRoot;
    @InjectView(R.id.scrollview)
    GradationScrollView scrollview;
    @InjectView(R.id.tv_usercenter)
    TextView tvUsercenter;
    @InjectView(R.id.ib_user_setting)
    ImageButton ibUserSetting;
    @InjectView(R.id.ib_user_message)
    ImageButton ibUserMessage;
    private int height;

    @Override
    public View initview() {
        View view = View.inflate(context, R.layout.fragment_user, null);
        ButterKnife.inject(this, view);
        tvUsercenter.setBackgroundColor(Color.argb((int) 0, 255,0,0));
        tvUsercenter.setTextColor(Color.argb((int) 0, 255,255,255));
        return view;
    }

    @Override
    public void initdata() {
        super.initdata();
        initListener();
    }

    private void initListener() {
        ViewTreeObserver viewTree = rlHeader.getViewTreeObserver();

        viewTree.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //移除监听
                rlHeader.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);

                //得到相对布局的高
                height = rlHeader.getHeight();

                //设置ScrollView的滑动监听
                scrollview.setScrollViewListener(UserPagerFragment.this);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_username, R.id.ib_user_setting, R.id.ib_user_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_username:
                Intent intent=new Intent(context,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.ib_user_setting:
                Toast.makeText(context, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_user_message:
                Toast.makeText(context, "消息", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {   //设置标题的背景颜色
            tvUsercenter.setBackgroundColor(Color.argb((int) 0, 255,0,0));

        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变

            //滑动的距离：最大距离（相对布局高度） = 透明的改变 ： 最大透明度

            //透明的改变 =  (滑动的距离/最大距离)*255

            //(滑动的距离/最大距离)
            float scale = (float) y / height;
            //透明度
            float alpha = ( scale*255);
            tvUsercenter.setTextColor(Color.argb((int) alpha, 255,255,255));
            tvUsercenter.setBackgroundColor(Color.argb((int) alpha, 255,0,0));
        } else {    //滑动到banner下面设置普通颜色
            //y>height
            //透明度：0~255
            tvUsercenter.setBackgroundColor(Color.argb((int) 255, 255,0,0));
        }
    }
}
