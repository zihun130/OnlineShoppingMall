package atguigu.com.onlineshoppingmall.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import atguigu.com.onlineshoppingmall.R;
import atguigu.com.onlineshoppingmall.adapter.HomeAdapter;
import atguigu.com.onlineshoppingmall.base.BaseFragment;
import atguigu.com.onlineshoppingmall.domain.HomePagerBean;
import atguigu.com.onlineshoppingmall.utils.Constants;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by sun on 2017/6/11.
 */

public class HomePagerFragment extends BaseFragment {

    @InjectView(R.id.tv_search_home)
    TextView tvSearchHome;
    @InjectView(R.id.tv_message_home)
    TextView tvMessageHome;
    @InjectView(R.id.rv_home)
    RecyclerView rvHome;
    @InjectView(R.id.ib_top)
    ImageButton ibTop;
    private HomeAdapter adapter;
    private HomePagerBean.ResultBean result;

    @Override
    public View initview() {
        View view = View.inflate(context, R.layout.fragment_homepager, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initdata() {
        super.initdata();
        
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(Constants.HOME_URL)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        proressData(response);
                    }
                });
    }

    private void proressData(String response) {
        HomePagerBean homeBean= JSON.parseObject(response,HomePagerBean.class);
        result = homeBean.getResult();

        adapter=new HomeAdapter(context,result);
        rvHome.setAdapter(adapter);
        //设置布局管理器

        GridLayoutManager liner=new GridLayoutManager(context,1);
        liner.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                if(position<=4){
                    ibTop.setVisibility(View.GONE);
                }else {
                    ibTop.setVisibility(View.VISIBLE);
                }
                return 1;
            }
        });
        rvHome.setLayoutManager(liner);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_search_home, R.id.tv_message_home, R.id.ib_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search_home:
                Toast.makeText(context, "输入搜索信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_message_home:
                Toast.makeText(context, "进入消息中心", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_top:
                rvHome.scrollToPosition(0);
                break;
        }
    }

}
