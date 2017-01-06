package com.geebit.app1.utils;/* data: 2017-01-06
 * author: 段海鹏
 * ui: 
 */

import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarUtil {
    public static void setSeekBarGps(SeekBar seekBar, TextView textView){
        int position = seekBar.getProgress(); //seekbar当前进度
        float x = seekBar.getWidth();//seekbar的当前位置
        float seekbarWidth = seekBar.getX() ; //seekbar的宽度
        float width = (position*x)/100+seekbarWidth; //seekbar当前位置的宽度
        textView.setX((float) (width-textView.getWidth()/1.2));
    }
}
