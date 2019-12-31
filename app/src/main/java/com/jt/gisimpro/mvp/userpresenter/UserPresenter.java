package com.jt.gisimpro.mvp.userpresenter;

import com.jt.commonmodule.callback.ResultCallback;
import com.jt.gisimpro.mvp.bean.LoginBean;
import com.jt.gisimpro.mvp.bean.ModifyBean;
import com.jt.gisimpro.mvp.bean.RegisterBean;
import com.jt.gisimpro.mvp.contract.UserContract;
import com.jt.gisimpro.mvp.usermodel.UserModel;

/**
 * @author 贾婷
 * @date 2019/12/29.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class UserPresenter extends UserContract.BaseUserPresenter {

    private UserModel model;

    public UserPresenter() {
        this.model = new UserModel();
    }

    /**
     * 注册
     * @param registerBean
     */
    @Override
    public void getResgister(RegisterBean registerBean) {
        model.RegisterData(registerBean, new ResultCallback<RegisterBean>() {
            @Override
            public void onSuccess(RegisterBean result) {
                mView.backSuccess(result);
            }

            @Override
            public void onFailed() {
                mView.backFailed();
            }
        });
    }

    @Override
    public void getLogin(LoginBean loginBean) {
        model.LoginData(loginBean, new ResultCallback<LoginBean>() {
            @Override
            public void onSuccess(LoginBean result) {
                mView.backSuccess(result);
            }

            @Override
            public void onFailed() {
                mView.backFailed();
            }
        });
    }

    @Override
    public void getModify(String uname, String pwd) {
        model.ModifyData(uname, pwd, new ResultCallback<ModifyBean>() {

            @Override
            public void onSuccess(ModifyBean result) {
                mView.backSuccess(result);
            }

            @Override
            public void onFailed() {

            }
        });
    }
}
