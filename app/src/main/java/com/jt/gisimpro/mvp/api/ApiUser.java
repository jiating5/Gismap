package com.jt.gisimpro.mvp.api;

import com.jt.basemodule.base.BaseBeanEntity;
import com.jt.gisimpro.mvp.bean.LoginBean;
import com.jt.gisimpro.mvp.bean.ModifyBean;
import com.jt.gisimpro.mvp.bean.RegisterBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author 贾婷
 * @date 2019/12/29.
 * GitHub：https://github.com/jiating5
 * description：用户的接口
 * */
public interface ApiUser {
    /**
     * 注册
     * @param registerBean
     * @return
     */
    @POST("api/User/register")
    Observable<BaseBeanEntity<RegisterBean>> register(@Body RegisterBean registerBean);

    /**
     * 登录
     * @param loginBean
     * @return
     */
    @POST("api/User/login")
    Observable<BaseBeanEntity<LoginBean>> login(@Body LoginBean loginBean);

    /**
     * 修改密码
     *
     */
    @FormUrlEncoded
    @POST("api/User/modifyPwd")
    Observable<BaseBeanEntity<ModifyBean>> modify(@Field("userid")String userid,@Field("pwd")String pwd);
}
