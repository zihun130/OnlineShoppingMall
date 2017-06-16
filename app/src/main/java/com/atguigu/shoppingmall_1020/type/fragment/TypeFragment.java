package com.atguigu.shoppingmall_1020.type.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.atguigu.shoppingmall_1020.R;
import com.atguigu.shoppingmall_1020.base.BaseFragment;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 作者：尚硅谷-杨光福 on 2017/2/22 11:36
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：分类Fragment
 */
public class TypeFragment extends BaseFragment {
    @InjectView(R.id.tl_1)
    com.flyco.tablayout.SegmentTabLayout tl1;
    @InjectView(R.id.iv_type_search)
    ImageView ivTypeSearch;
    private ArrayList<BaseFragment> fragments;
    private java.lang.String[] titles={"分类","标签"};
    private Fragment tempfragment;
    private Fragment currentFagment;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type, null);
        ButterKnife.inject(this, view);
        return view;
    }

    /**
     * 1.把数据绑定到控件上的时候，重新该方法
     * 2.联网请求，把得到的数据绑定到视图上
     */
    @Override
    public void initData() {
        super.initData();

        tl1.setTabData(titles);
        tl1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchFragment(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        initFragment();
    }

    private void switchFragment(int position) {

        currentFagment=fragments.get(position);

        if(tempfragment!=currentFagment){
            if(currentFagment!=null){
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                if(!currentFagment.isAdded()){
                    if(tempfragment!=null){
                        ft.hide(tempfragment);
                    }
                    ft.add(R.id.fl_type,currentFagment);
                }else {
                    if(tempfragment!=null){
                        ft.hide(tempfragment);
                    }
                    ft.show(currentFagment);
                }

                ft.commit();
            }
            tempfragment=currentFagment;
        }
    }

    private void initFragment() {
        fragments=new ArrayList<>();
        fragments.add(new ListViewFragment());
        fragments.add(new TagFrament());
        switchFragment(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.iv_type_search)
    public void onViewClicked() {
        Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
    }
}
