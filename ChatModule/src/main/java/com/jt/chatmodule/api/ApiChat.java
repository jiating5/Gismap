package com.jt.chatmodule.api;

import com.jt.basemodule.base.BaseBeanEntity;
import com.jt.chatmodule.view.bean.FriendBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author 贾婷
 * @date 2020/1/3.
 * GitHub：https://github.com/jiating5
 * description：
 */
public interface ApiChat {
    /**
     * 获取好友列表
     * @param usercode
     * @return
     */
    @GET("api/Friend/getFriends")
    Observable<BaseBeanEntity<List<FriendBean>>> friendData(@Query("usercode") String usercode);

    /**
     * 搜索好友
     */
    @GET("api/Friend/searchFriend")
    Observable<BaseBeanEntity<List<FriendBean>>> searchfriendData(@Query("username") String username,@Query("nick")String nick);

    /**
     * 添加好友
     */
    @POST("api/Friend/addFriend")
    Observable<BaseBeanEntity> addfriendData(@Query("usercode") String usercode, @Query("friendcode") String friendcode);

}
