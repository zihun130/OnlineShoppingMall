package atguigu.com.onlineshoppingmall.home;

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

        adapter=new HomeAdapter(context,HomePagerBean);
        rvHome.setAdapter(adapter);
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
                Toast.makeText(context, "返回", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
