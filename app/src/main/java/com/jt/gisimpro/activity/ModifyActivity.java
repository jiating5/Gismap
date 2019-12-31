package com.jt.gisimpro.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.WorkerThread;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jt.basemodule.activity.BaseMVPActivity;
import com.jt.commonmodule.arouter.ARouterConfig;
import com.jt.gisimpro.R;
import com.jt.gisimpro.mvp.bean.ModifyBean;
import com.jt.gisimpro.mvp.contract.UserContract;
import com.jt.gisimpro.mvp.userpresenter.UserPresenter;

@Route(path = ARouterConfig.APP_MODIFY)
public class ModifyActivity extends BaseMVPActivity<UserContract.BaseUserPresenter> implements UserContract.IUserView {

    @Autowired
    String username;
    EditText uname,pwd,repwd;
    Button modity;
    UserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void doEvent() {

    }

    @Override
    protected void initData() {
        modity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPresenter.getModify(uname.getText().toString(),pwd.getText().toString());
            }
        });
    }

    @Override
    protected void initViews() {
        uname = findViewById(R.id.modity_uname);
        pwd = findViewById(R.id.modity_pwd);
        repwd = findViewById(R.id.modity_repwd);
        modity = findViewById(R.id.modity_modity);
        userPresenter = new UserPresenter();
        userPresenter.AttachView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify;
    }

    @Override
    public void backSuccess(Object message) {
        ModifyBean bean = (ModifyBean) message;
//        if (bean.getMsg().equals("操作成功")){
//            Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
//            finish();
//        }
        if (bean!=null){
            finish();
        }
    }

    @Override
    public void backFailed() {

    }

    @Override
    public void showLoad() {

    }

    @Override
    public void hideLoad() {

    }
}
