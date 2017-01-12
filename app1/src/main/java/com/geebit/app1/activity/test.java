package com.geebit.app1.activity;


import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by DEll on 2016-12-30.
 */
public class test {
    public static void main (String[]args) throws IOException {

        /*Calendar calendar   =   new GregorianCalendar();
        Date date = new Date();
        calendar.setTime(date);
        calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime();
        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("MM-dd E");
        String mTime = formatter.format(date);
        System.out.println(mTime);
        SimpleDateFormat formatter1   =   new   SimpleDateFormat   ("MM-dd E");
        Date date1 = new Date();
        String mTime1 = formatter1.format(date1);
        System.out.println(mTime1);
        System.out.println(mTime.equals(mTime1));*/
        /*String md5 = CrmApiUtil.getMd5("aa123456");
        System.out.println(md5);*/
        URL url = new URL("http://open.baidu.com/special/time/");
        URLConnection uc = url.openConnection();
        uc.connect();
        long id = uc.getDate();
        Date date = new Date(id);
        Calendar calendar   =   new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd E");
       String nowTime = format.format(date);
        System.out.println(nowTime);

    }

}
