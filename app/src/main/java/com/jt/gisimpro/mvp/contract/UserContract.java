package com.jt.gisimpro.mvp.contract;

import com.jt.basemodule.base.BaseBeanEntity;
import com.jt.basemodule.base.BasePresenter;
import com.jt.basemodule.base.IModel;
import com.jt.basemodule.base.IView;
import com.jt.commonmodule.callback.ResultCallback;
import com.jt.gisimpro.mvp.bean.LoginBean;
import com.jt.gisimpro.mvp.bean.ModifyBean;
import com.jt.gisimpro.mvp.bean.RegisterBean;

import io.reactivex.Observable;

/**
 * @author 贾婷
 * @date 2019/12/29.
 * GitHub：https://github.com/jiating5
 * description：用户契约类
 */
public interface UserContract {

    interface IUserModel extends IModel{
//        Observable<BaseBeanEntity<RegisterBean>> RegisterData(RegisterBean registerBean);
        void RegisterData(RegisterBean registerBean,ResultCallback<RegisterBean> callback);
        void LoginData(LoginBean loginBean, ResultCallback<LoginBean> callback);
//        Observable<ModifyBean> ModifyData(String uname, String pwd);
        void ModifyData(String uname, String pwd, ResultCallback<ModifyBean> callback);
    }

    interface IUserView<T> extends IView{
        void backSuccess(T message);
        void backFailed();
    }

    abstract class BaseUserPresenter extends BasePresenter<IUserView,IUserModel>{
        abstract public void getResgister(RegisterBean registerBean);
        abstract public void getLogin(LoginBean loginBean);
        abstract public void getModify(String uname, String pwd);
    }
}
