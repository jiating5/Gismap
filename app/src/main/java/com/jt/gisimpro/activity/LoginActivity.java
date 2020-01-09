package com.jt.gisimpro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jt.basemodule.activity.BaseMVPActivity;
import com.jt.commonmodule.arouter.ARouterConfig;
import com.jt.commonmodule.sp.spUtils;
import com.jt.gisimpro.R;
import com.jt.gisimpro.XmppService;
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
    CheckBox cb_save;
//    spUtils spUtils = new spUtils(this);
    UserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, XmppService.class));
    }

    @Override
    protected void doEvent() {
        //登录成功，跳转主页
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //记住密码
                if (cb_save.isChecked()){
//                    spUtils.SaveSp(uname.getText().toString(),pwd.getText().toString());
                }
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
        cb_save = findViewById(R.id.login_cb_save);
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
            String usercode = bean.getUsercode();
            ARouter.getInstance().build(ARouterConfig.HOMEMODULE_MAP).withString("usercode",usercode).navigation();
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        }
    }

    @Override
    public void backFailed() {

    }
}
