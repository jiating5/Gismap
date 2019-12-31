package com.jt.gisimpro.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jt.basemodule.activity.BaseMVPActivity;
import com.jt.commonmodule.arouter.ARouterConfig;
import com.jt.gisimpro.R;
import com.jt.gisimpro.mvp.bean.RegisterBean;
import com.jt.gisimpro.mvp.contract.UserContract;
import com.jt.gisimpro.mvp.userpresenter.UserPresenter;

/**
 * @author 贾婷
 * @date 2019/12/29.
 * GitHub：https://github.com/jiating5
 * description：注册用户
 */
@Route(path = ARouterConfig.APP_REGISTER)
public class RegisterActivity extends BaseMVPActivity<UserContract.BaseUserPresenter> implements UserContract.IUserView {

    Button register;
    EditText uname,pwd,repwd;
    UserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void doEvent() {
        //注册成功，返回登录
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    RegisterBean bean = new RegisterBean(1, "", uname.getText().toString(), uname.getText().toString(), "女", "2019/12/30", "", "", 1, "", "", 1, 1);
                    userPresenter.getResgister(bean);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        uname = findViewById(R.id.register_uname);
        pwd = findViewById(R.id.register_pwd);
        repwd = findViewById(R.id.register_repwd);
        register = findViewById(R.id.register_register);
        userPresenter = new UserPresenter();
        userPresenter.AttachView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void showLoad() {

    }

    @Override
    public void hideLoad() {

    }

    @Override
    public void backSuccess(Object message) {
        RegisterBean registerBean = (RegisterBean) message;
        if (registerBean!=null){
            Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this,"注册失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void backFailed() {

    }

}
