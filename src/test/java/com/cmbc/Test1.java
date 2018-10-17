package com.cmbc;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhuzengpeng
 * @date 2018/10/13 17:08
 */
public class Test1 {


    public static Date timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return null;
        }
        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return new Date(Long.valueOf(seconds));
    }

    @Test
    public void test1() {
        System.out.println(timeStamp2Date("1531702862640", null));
        System.out.println(new Date().getTime());
    }
}
