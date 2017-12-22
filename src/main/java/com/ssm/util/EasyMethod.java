package com.ssm.util;

import java.util.regex.Matcher;
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


    /**
     * 判断是否是电话号码
     * @param tele 传入的电话号码
     * @return 符合正则表达式返回true，否则返回false
     */
    public static boolean ValidateTel(String tele){
        Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
        Matcher matcher = regex.matcher(tele);
        boolean result = matcher.matches();
        return result;
    }

    /**
     * 判断是否是邮箱
     * @param str 传入的邮箱
     * @return 符合返回true，否则返回false
     */
    public static boolean ValidateEmail(String str){
        Pattern regex = Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Matcher matcher = regex.matcher(str);
        return matcher.matches();
    }


    /**
     * 判断是否有数字，字母组成
     * @param str 需要判断的字符串
     * @return 符合返回true，否则返回false
     */
    public static boolean ValidateString(String str){
        Pattern regex = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher matcher = regex.matcher(str);
        return matcher.matches();
    }

}
