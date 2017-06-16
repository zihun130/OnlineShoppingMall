package com.atguigu.shoppingmall_1020.app;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.atguigu.shoppingmall_1020.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CallcenterActivity extends AppCompatActivity {

    @InjectView(R.id.wv_view)
    WebView wvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callcenter);
        ButterKnife.inject(this);

        initWebView();
    }

    private void initWebView() {
        WebSettings settings = wvView.getSettings();
        //支持JavaScript
        settings.setJavaScriptEnabled(true);
        //支持双击缩放
        settings.setUseWideViewPort(true);
        //禁止跳转第三方浏览器
        wvView.setWebViewClient(new WebViewClient(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                wvView.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        wvView.loadUrl("http://www6.53kf.com/webCompany.php?arg=10007377&style=2&kflist=off&kf=info@atguigu.com,video@atguigu.com,public@atguigu.com,3069368606@qq.com,215648937@qq.com,sudan@atguigu.com,sszhang@atguigu.com&zdkf_type=1&language=zh-cn&charset=gbk&referer=http%3A%2F%2Fatguigu.com%2F&keyword=&tfrom=1&tpl=crystal_blue&uid=94453f39187416b3d434b974b8dccc0c&timeStamp=1482482675132&ucust_id=");
    }
}
