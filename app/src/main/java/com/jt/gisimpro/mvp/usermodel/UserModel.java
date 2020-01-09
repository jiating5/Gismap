package com.jt.gisimpro.mvp.usermodel;

import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.contract.IXmppUser;
import com.jt.basemodule.base.BaseBeanEntity;
import com.jt.basemodule.base.BaseObservable;
import com.jt.basemodule.base.BaseObserver;
import com.jt.basemodule.net.RetrofitUtils;
import com.jt.commonmodule.callback.ResultCallback;
import com.jt.gisimpro.mvp.api.ApiUser;
import com.jt.gisimpro.mvp.bean.LoginBean;
import com.jt.gisimpro.mvp.bean.ModifyBean;
import com.jt.gisimpro.mvp.bean.RegisterBean;
import com.jt.gisimpro.mvp.contract.UserContract;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * @author 贾婷
 * @date 2019/12/29.
 * GitHub：https://github.com/jiating5
 * description：用户M层
 */
public class UserModel implements UserContract.IUserModel {
    ApiUser apiUser = RetrofitUtils.getInstance().create(ApiUser.class);
    BaseObservable observable = new BaseObservable();

    /**
     * 请求注册
     * @param registerBean
     * @param callback
     */
    @Override
    public void RegisterData(final RegisterBean registerBean, final ResultCallback<RegisterBean> callback) {
        Observable<Boolean> booleanObservable = Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                XmppManager.getInstance().getXmppUserManager().createAccount(registerBean.getUsername(), registerBean.getPwd());
                emitter.onNext(true);
            }
        });
        observable.doObservable(apiUser.register(registerBean),booleanObservable,new BaseObserver<Object>(){
            @Override
            public void onNext(Object o) {
                super.onNext(o);
                if(o instanceof BaseBeanEntity){
                    ((BaseBeanEntity) o).getMsg();
                    callback.onSuccess((RegisterBean) ((BaseBeanEntity) o).getData());
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                callback.onFailed();
            }
        });

    }

    /**
     * 请求登录
     * @param loginBean
     * @param callback
     */
    @Override
    public void LoginData(final LoginBean loginBean, final ResultCallback<LoginBean> callback) {
        Observable<Boolean> booleanObservable = Observable.create(new ObservableOnSubscribe<Boolean>() {

            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                XmppManager.getInstance().getXmppUserManager().login(loginBean.getUsername(), (String) loginBean.getPwd());
                emitter.onNext(true);
            }
        });

        observable.doObservable(apiUser.login(loginBean),booleanObservable,new BaseObserver(){
            @Override
            public void onNext(Object o) {
                super.onNext(o);
                if (o instanceof BaseBeanEntity){
                    callback.onSuccess((LoginBean) ((BaseBeanEntity) o).getData());
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    /**
     * 修改密码
     * @param uname
     * @param pwd
     * @param callback
     */
    @Override
    public void ModifyData(final String uname, final String pwd, final ResultCallback<ModifyBean> callback) {
        Observable<Boolean> booleanObservable = Observable.create(new ObservableOnSubscribe<Boolean>() {

            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                XmppManager.getInstance().getXmppUserManager().changePassword(pwd);
                emitter.onNext(true);
            }
        });

        observable.doObservable(apiUser.modify(uname,pwd),booleanObservable,new BaseObserver(){
            @Override
            public void onNext(Object o) {
                super.onNext(o);
                if (o instanceof ModifyBean){
                    callback.onSuccess((ModifyBean) o);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    @Override
    public void Destory() {
        apiUser = null;
        observable = null;
    }
}
