package com.can.mvp.utils;

/**
 * Created by can on 2018/4/3.
 */

public class StringUtils {

    /**
     * 字符串是否为空
     * @param string
     * @return
     */
    public static boolean isEmpty(String string){
        return  string==null||string.equals("")?true:false;
    }

}
