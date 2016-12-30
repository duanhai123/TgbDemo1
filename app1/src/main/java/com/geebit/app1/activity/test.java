package com.geebit.app1.activity;

import com.geebit.app1.utils.CrmApiUtil;

/**
 * Created by DEll on 2016-12-30.
 */
public class test {
    public static void main (String[]args){
        String md5 = CrmApiUtil.getMD5("aa123455");
        System.out.println(md5);

    }
}
