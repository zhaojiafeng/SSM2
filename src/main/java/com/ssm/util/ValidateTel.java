package com.ssm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dllo on 17/12/19.
 */
public class ValidateTel {

    public static boolean ValidateTel(String tele){
        Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
        Matcher matcher = regex.matcher(tele);
        boolean result = matcher.matches();
        return result;
    }

}
