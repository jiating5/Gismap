package com.jt.basemodule.api;

import com.jt.basemodule.bean.TokenBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author 贾婷
 * @date 2019/12/29.
 * GitHub：https://github.com/jiating5
 * description：
 */
public interface apiService {
    @POST("/token")
    @FormUrlEncoded
    Call<TokenBean> getToken(@Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);
}
