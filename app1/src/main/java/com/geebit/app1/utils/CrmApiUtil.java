package com.geebit.app1.utils;


import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class CrmApiUtil {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static String postOnlyJson(String url,String postBody) {


        OkHttpClient client = new OkHttpClient();
        String stsJson = "";
        Request request = new Request.Builder().url(url).post(RequestBody.create(JSON, postBody))
                .build();
        ;
        try{
            Response response = client.newCall(request).execute();
            stsJson = response.body().string();
        }catch (IOException e) {
            e.printStackTrace();
            Log.e("postFail", e.toString());
        }

        return stsJson;
    }
    //提交tel,possword获取登录注册
    public static String postMap(String tel,String password){
        HashMap map = new HashMap();
        map.put("tel",tel);
        map.put("password",password);
        JSONObject object = new JSONObject(map);
        return object.toString();
    }
    //提交id获取推荐码
    public static String postMap(String userid){
        HashMap map = new HashMap();
        map.put("user_id",userid);
        JSONObject object = new JSONObject(map);
        return object.toString();
    }
    //MD5加密
    public static String getMd5(String password){
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把没一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

    }


}