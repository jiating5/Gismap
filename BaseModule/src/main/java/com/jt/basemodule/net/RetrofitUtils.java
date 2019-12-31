package com.jt.basemodule.net;

import android.util.Log;

import com.jt.basemodule.api.apiService;
import com.jt.basemodule.bean.TokenBean;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 贾婷
 * @date 2019/12/27.
 * GitHub：https://github.com/jiating5
 * description：Rx网络请求
 */

public class RetrofitUtils {

    private String StringUri = "http://api.zydeveloper.com:10001/";
    private Retrofit mRetrofit;

    private static volatile RetrofitUtils singleton;

    public static RetrofitUtils getInstance() {
        if (singleton == null) {
            synchronized (RetrofitUtils.class) {
                if (singleton == null) {
                    singleton = new RetrofitUtils();
                }
            }
        }
        return singleton;
    }

    private RetrofitUtils() {
        OkHttpClient builder = new OkHttpClient.Builder()
                .addInterceptor(getInterceptor())
                .addNetworkInterceptor(getLogInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15,TimeUnit.SECONDS)
                .writeTimeout(15,TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(StringUri)
                .client(builder)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Log拦截器
     */
    private Interceptor getLogInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("Tag",message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    /**
     * 自定义拦截器
     */
    private Interceptor getInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //拦截请求
                Request request = chain.request();
                //拦截相应
                Response proceed = chain.proceed(request);
                Request.Builder builder = request.newBuilder()
                        .addHeader("Content-Type","application-json");
                if (proceed.code() == 401){
                    builder.addHeader("Authorization","bearer " + getToken());
                }
                return chain.proceed(builder.build());
            }
        };
    }

    //获取token
    private String getToken() {
        try {
            TokenBean body = mRetrofit.create(apiService.class)
                    .getToken("password", "341de11517517819a16213218f10712d1df1fa1221471591", "")
                    .execute().body();
            return body.getAccess_token();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

}