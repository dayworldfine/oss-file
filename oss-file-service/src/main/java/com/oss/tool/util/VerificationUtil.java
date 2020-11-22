package com.oss.tool.util;

import java.util.regex.Pattern;

public class VerificationUtil {

    private static final String regexPhone = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9])|(19[0,5-9]))\\d{8}$";

    private static final String regexName = "^[\\u4e00-\\u9fa5a-zA-Z][\\u4e00-\\u9fa5a-zA-Z\\d]{2,15}$";

    public static boolean checkPhone(String phone) {
        if (ValidateUtil.isEmpty(phone)) {
            return false;
        }
        return Pattern.compile(regexPhone).matcher(phone).matches();
    }

    public static boolean checkName(String name) {
        if (ValidateUtil.isEmpty(name)) {
            return false;
        }
        return Pattern.compile(regexName).matcher(name).matches();
    }

    public static void main(String[] args) {
        String name = "S1张三撒大声地所多所颠三倒四颠三";
        System.out.println(checkName(name));
    }
}
