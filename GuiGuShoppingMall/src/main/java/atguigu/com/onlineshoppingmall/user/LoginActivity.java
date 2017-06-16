package atguigu.com.onlineshoppingmall.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import atguigu.com.onlineshoppingmall.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.ib_login_back)
    ImageButton ibLoginBack;
    @InjectView(R.id.et_login_phone)
    EditText etLoginPhone;
    @InjectView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @InjectView(R.id.ib_login_visible)
    ImageButton ibLoginVisible;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.tv_login_register)
    TextView tvLoginRegister;
    @InjectView(R.id.tv_login_forget_pwd)
    TextView tvLoginForgetPwd;
    @InjectView(R.id.ib_weibo)
    ImageButton ibWeibo;
    @InjectView(R.id.ib_qq)
    ImageButton ibQq;
    @InjectView(R.id.ib_wechat)
    ImageButton ibWechat;
    @InjectView(R.id.activity_login)
    LinearLayout activityLogin;
    private boolean isChecked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.ib_login_back, R.id.ib_login_visible, R.id.btn_login, R.id.tv_login_register, R.id.tv_login_forget_pwd, R.id.ib_weibo, R.id.ib_qq, R.id.ib_wechat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_login_back:
                finish();
                break;
            case R.id.ib_login_visible:
                isChecked=!isChecked;
                if (isChecked) {
                    ibLoginVisible.setBackgroundResource(R.drawable.new_password_drawable_visible);
                    etLoginPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    //光标定位到末尾
                    etLoginPwd.setSelection(etLoginPwd.length());
                } else {
                    ibLoginVisible.setBackgroundResource(R.drawable.new_password_drawable_invisible);
                    etLoginPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    //光标定位到末尾
                    etLoginPwd.setSelection(etLoginPwd.length());
                }
                break;
            case R.id.btn_login:
                Toast.makeText(this, "登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_login_register:
                Toast.makeText(this, "注册", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_login_forget_pwd:
                Toast.makeText(this, "找回密码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_weibo:
                Toast.makeText(this, "微博登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_qq:
                Toast.makeText(this, "QQ登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_wechat:
                Toast.makeText(this, "微信登录", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
