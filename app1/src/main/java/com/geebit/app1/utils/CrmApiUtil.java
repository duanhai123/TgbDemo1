package com.geebit.app1.utils;


import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

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
}