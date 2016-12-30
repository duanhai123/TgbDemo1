package com.geebit.app1.utils;


import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    public static String getMD5(String info)
    {
        try
        {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();

            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++)
            {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1)
                {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                }
                else
                {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }

            return strBuf.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            return "";
        }
        catch (UnsupportedEncodingException e)
        {
            return "";
        }
    }

}