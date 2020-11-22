package com.oss.tool;

import java.util.LinkedHashMap;
import java.util.Map;

public class ErrorCodes {
    private static Map<Integer, String> errorCodes = new LinkedHashMap(100);
    public static final int OK = 10000;
    public static final int SERVER_INTERNAL_ERROR = -10000;
    public static final int PARAM_VALID_ERROR = 10001;
    public static final int PARAM_EMPTY_ERROR = 10002;
    public static final int BANGLE_EXICT = 20001;
    public static final int FILE_SUFFIX = 20002;
    public static final int FILE_NULL = 20003;
    public static final int ADD_ERROR = 20004;
    public static final int SELECT_NOT_FOUND = 20005;
    public static final int FILE_NAME_HAVE = 20006;
    public static final int GET_FILE_URL_ERROR = 20007;
    public static final int ADD_FILE_STATISTICS_ERROR = 20008;
    public static final int ERROR_ZONE_KEY = 20009;
    public static final int ERROR_ZONE_IS_HAVE = 20010;
    public static final int ZONE_REPETITION = 20011;
    public static final int PHONE_ERROR = 20012;
    public static final int CODE_ERROR = 20013;
    public static final int PHONE_IS_HAVE = 20014;
    public static final int NO_PERMISSION = 20015;
    public static final int USER_PWD_ERROR = 20016;


    public static final int SERVICE_SNEAK_OFF= 99999;

    public ErrorCodes() {
    }

    public static String get(int code) {
        return (String)errorCodes.get(code);
    }

    static {
        errorCodes.put(10000, "成功");
        errorCodes.put(-10000, "服务器内部错误");
        errorCodes.put(10001, "参数校验错误");
        errorCodes.put(10002, "缺少必传参数");
        errorCodes.put(20001, "未能完全删除");
        errorCodes.put(20002, "上传出现意外错误，请检查文件格式");
        errorCodes.put(20003, "文件不得为空");
        errorCodes.put(20004, "添加数据不成功");
        errorCodes.put(20005, "查询不到数据");
        errorCodes.put(20006, "文件名已存在");
        errorCodes.put(20007, "获取预览地址失败");
        errorCodes.put(20008, "添加文件下载或预览次数失败");
        errorCodes.put(20009, "分区密匙无效");
        errorCodes.put(20010, "已经拥有该分区");
        errorCodes.put(20011, "分区的名字或前缀或密匙重复");
        errorCodes.put(20012, "请输入正确的手机号");
        errorCodes.put(20013, "验证码错误");
        errorCodes.put(20014, "该手机号已被注册");
        errorCodes.put(20015, "没有权限");
        errorCodes.put(20016, "账号密码错误");
        errorCodes.put(99999, "服务器正在开小差~~");
    }
}
