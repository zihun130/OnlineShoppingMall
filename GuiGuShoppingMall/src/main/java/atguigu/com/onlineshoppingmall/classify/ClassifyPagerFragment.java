package atguigu.com.onlineshoppingmall.classify;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import atguigu.com.onlineshoppingmall.R;
import atguigu.com.onlineshoppingmall.base.BaseFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by sun on 2017/6/11.
 */

public class ClassifyPagerFragment extends BaseFragment {

    @InjectView(R.id.tl_1)
    SegmentTabLayout tl1;
    @InjectView(R.id.iv_type_search)
    ImageView ivTypeSearch;
    @InjectView(R.id.fl_type)
    FrameLayout flType;
    private String[] titles={"分类","标签"};
    private ArrayList<BaseFragment> fragments;
    private Fragment tempfragment;

    @Override
    public View initview() {
        View view = View.inflate(context, R.layout.fragment_classify, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initdata() {
        super.initdata();

        initSegmentTabLayout();
        initFragment();
    }

    private void initSegmentTabLayout() {
        tl1.setTabData(titles);
        tl1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchFragment(fragments.get(position));
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }


    private void initFragment() {
        fragments=new ArrayList<>();
        fragments.add(new ListViewFragment());
        fragments.add(new LableActivity());

        switchFragment(fragments.get(0));
    }


    private void switchFragment(BaseFragment currentFagment) {
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.iv_type_search)
    public void onViewClicked() {
        Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
    }
}
