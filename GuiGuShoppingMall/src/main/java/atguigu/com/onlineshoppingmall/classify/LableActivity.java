package atguigu.com.onlineshoppingmall.classify;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.onlineshoppingmall.R;
import atguigu.com.onlineshoppingmall.adapter.classifyadapter.LableAdapter;
import atguigu.com.onlineshoppingmall.base.BaseFragment;
import atguigu.com.onlineshoppingmall.domain.LableBean;
import atguigu.com.onlineshoppingmall.utils.Constants;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by sun on 2017/6/15.
 */

public class LableActivity extends BaseFragment {
    @InjectView(R.id.gv_tag)
    GridView gvTag;
    private List<LableBean.ResultBean> result;
    private LableAdapter adapter;

    @Override
    public View initview() {
        View view = View.inflate(context, R.layout.fragment_lable, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initdata() {
        super.initdata();
        getLableFromNet(Constants.TAG_URL);
    }

    private void getLableFromNet(String tagUrl) {
        OkHttpUtils
                .get()
                .url(tagUrl)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        processData(response);
                    }
                });
    }

    private void processData(final String response) {
        LableBean lableBean = JSON.parseObject(response, LableBean.class);
        result = lableBean.getResult();
        adapter=new LableAdapter(context,result);
        gvTag.setAdapter(adapter);

        gvTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,result.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
