package com.jt.gisimpro.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jt.basemodule.activity.BaseMVPActivity;
import com.jt.commonmodule.arouter.ARouterConfig;
import com.jt.gisimpro.R;
import com.jt.gisimpro.mvp.bean.LoginBean;
import com.jt.gisimpro.mvp.contract.UserContract;
import com.jt.gisimpro.mvp.userpresenter.UserPresenter;

/**
 * @author 贾婷
 * @date 2019/12/29.
 * GitHub：https://github.com/jiating5
 * description：用户登录
 */
public class LoginActivity extends BaseMVPActivity<UserContract.BaseUserPresenter> implements UserContract.IUserView {

    Button button;
    TextView register,modity;
    EditText uname,pwd;
    UserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void doEvent() {
        //登录成功，跳转主页
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginBean loginBean = new LoginBean(1, "", uname.getText().toString(), pwd.getText().toString(), "", "", "", "", 1, "", "", 1, 1);
                userPresenter.getLogin(loginBean);
            }
        });
        //新用户注册
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ARouterConfig.APP_REGISTER).navigation();
            }
        });
        //忘记密码
        modity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ARouterConfig.APP_MODIFY).withString("username",uname.getText().toString()).navigation();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        modity = findViewById(R.id.login_forgetpwd);
        button = findViewById(R.id.login_login);
        uname = findViewById(R.id.login_uname);
        pwd = findViewById(R.id.login_pwd);
        register = findViewById(R.id.login_register);
        userPresenter = new UserPresenter();
        userPresenter.AttachView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void showLoad() {

    }

    @Override
    public void hideLoad() {

    }

    @Override
    public void backSuccess(Object message) {
        LoginBean bean = (LoginBean) message;
        if (bean!=null){
            ARouter.getInstance().build(ARouterConfig.HOMEMODULE_MAP).withString("success","登录成功").navigation();
        }
    }

    @Override
    public void backFailed() {

    }
}
