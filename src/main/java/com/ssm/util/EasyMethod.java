package com.ssm.util;

import java.util.regex.Pattern;

/**
 * Created by dllo on 17/12/21.
 */
public class EasyMethod {

    /*
    * 判断是否为整数
    * @param str 传入的字符串
    * @return 是整数返回true,否则返回false
    */
    public static boolean isInteger(String obj){
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(obj).matches();
    }







}
