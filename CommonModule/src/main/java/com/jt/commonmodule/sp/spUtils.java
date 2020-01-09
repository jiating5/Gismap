package com.jt.commonmodule.sp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class spUtils {

    private SharedPreferences sp;
    private String FILE_NAME = "user_save";
    private String USER_NAME = "user_name";
    private String USER_PASSWORD = "user_password";

    public spUtils(Context context) {
        sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
    }

    /**
     * sp存储
     * @param name
     * @param pwd
     */
    public void SaveSp(String name,String pwd){
        sp.edit().putString(USER_NAME,name)
                .putString(USER_PASSWORD,pwd).commit();
    }

    /**
     * sp读取
     */
    public Map<String,String> ReadSp(){
        Map<String, String> map = new HashMap<>();
        String name = sp.getString(USER_NAME, "");
        String pwd = sp.getString(USER_PASSWORD, "");
        map.put("name",name);
        map.put("password",pwd);
        return map;
    }
}