package atguigu.com.onlineshoppingmall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;

import atguigu.com.onlineshoppingmall.base.BaseFragment;
import atguigu.com.onlineshoppingmall.classify.ClassifyPagerFragment;
import atguigu.com.onlineshoppingmall.find.FindPagerFragment;
import atguigu.com.onlineshoppingmall.home.HomePagerFragment;
import atguigu.com.onlineshoppingmall.shoppingcart.ShoppingCartFragment;
import atguigu.com.onlineshoppingmall.user.UserPagerFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.fl_main)
    FrameLayout flMain;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;
    @InjectView(R.id.activity_main)
    LinearLayout activityMain;
    private ArrayList<BaseFragment> fragments;
    private int position;
    private Fragment tempfragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initFragment();

        initListener();
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home :
                        position=0;
                        break;
                    case R.id.rb_classify :
                        position=1;
                        break;
                    case R.id.rb_find :
                        position=2;
                        break;
                    case R.id.rb_cart :
                        position=3;
                        break;
                    case R.id.rb_user :
                        position=4;
                        break;
                }
                Fragment currfragment = fragments.get(position);
                switchFragment(currfragment);
            }

        });
        //选择首页
        rgMain.check(R.id.rb_home);
    }

    private void switchFragment(Fragment currfragment) {
        if(tempfragment!=currfragment){
            //开启事务
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            if(!currfragment.isAdded()){
                if(tempfragment!=null){
                    ft.hide(tempfragment);
                }
                //添加当前
                ft.add(R.id.fl_main,currfragment);
            }else {
                if(tempfragment!=null){
                    //隐藏缓存
                    ft.hide(tempfragment);
                }
                //显示当前
                ft.show(currfragment);
            }

            ft.commit();
            tempfragment=currfragment;
        }

    }

    private void initFragment() {
        fragments=new ArrayList<>();
        fragments.add(new HomePagerFragment());
        fragments.add(new ClassifyPagerFragment());
        fragments.add(new FindPagerFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserPagerFragment());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int id = intent.getIntExtra("checkedid",R.id.rb_home);
        switch (id){
            case R.id.rb_home:
                rgMain.check(R.id.rb_home);
                break;
            case R.id.rb_cart:
                rgMain.check(R.id.rb_cart);
                break;
        }
    }
}
