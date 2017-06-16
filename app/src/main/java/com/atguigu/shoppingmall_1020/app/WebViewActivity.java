package com.atguigu.shoppingmall_1020.app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.shoppingmall_1020.R;
import com.atguigu.shoppingmall_1020.adapter.HomePagerAdapter;
import com.atguigu.shoppingmall_1020.domain.GoodsBean;
import com.atguigu.shoppingmall_1020.domain.H5Bean;
import com.atguigu.shoppingmall_1020.domain.webviewbean;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.atguigu.shoppingmall_1020.adapter.HomePagerAdapter.GOODS_BEAN;

public class WebViewActivity extends AppCompatActivity {

    @InjectView(R.id.ib_back)
    ImageButton ibBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_more)
    ImageButton ibMore;
    @InjectView(R.id.webview)
    WebView webview;
    @InjectView(R.id.progressbar)
    ProgressBar progressbar;
    @InjectView(R.id.activity_web_view)
    LinearLayout activityWebView;
    private webviewbean web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.inject(this);

        getData();
        setData();
    }

    private void getData() {
        web = (webviewbean) getIntent().getSerializableExtra(HomePagerAdapter.WEBVIEW_BEAN);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressbar.setVisibility(View.GONE);
            }
        });

        webview.addJavascriptInterface(new MyInterface(),"cyc");

        //加载地址
        webview.loadUrl(web.getUrl());


    }

    private void setData() {

    }

    @OnClick({R.id.ib_back, R.id.ib_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.ib_more:
                Toast.makeText(WebViewActivity.this, "更多", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private class MyInterface {
        @JavascriptInterface
        public void jumpForAndroid(String data){

            H5Bean h5Bean = JSON.parseObject(data,H5Bean.class);
            int id = h5Bean.getValue().getProduct_id();
            String product_id = "";
            String name = "";
            String cover_price = "";
            if (id%3 == 0) {
                product_id = "627";
                cover_price = "32.00";
                name = "剑三T恤批发";
            } else if (id%3 == 1) {
                product_id = "21";
                cover_price = "8.00";
                name = "同人原创】剑网3 剑侠情缘叁 Q版成男 口袋胸针";
            } else {
                product_id = "1341";
                cover_price = "50.00";
                name = "【蓝诺】《天下吾双》 剑网3同人本";
            }
            String image = "/supplier/1478873740576.jpg";
            GoodsBean goodsBean = new GoodsBean();
            goodsBean.setName(name);
            goodsBean.setCover_price(cover_price);
            goodsBean.setFigure(image);
            goodsBean.setProduct_id(product_id);

            Intent intent = new Intent(WebViewActivity.this, GridInfoActivity.class);
            intent.putExtra(GOODS_BEAN, goodsBean);
            startActivity(intent);
        }
    }
}
