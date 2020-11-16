package com.oss.tool.util;

/**
 * @ClassName：EnumUtils
 * @Description: 枚举工具类
 * @Author：13738700108
 * @Data 2020/9/6 15:00
 * @Version: v1.0
 **/
public class EnumUtil {

    //常规判断枚举
    public static enum JUDGE_ENUM {
        NO("否", 0, "NO"),
        YES("被替换", 1, "YES");
        private String name;
        private int value;
        private String code;

        private JUDGE_ENUM(String name, int value, String code) {
            this.name = name;
            this.value = value;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    //文件状态枚举
    public static enum File_STATUS_ENUM {
        FORBIDDEN("禁用", 0, "FORBIDDEN"),
        BE_SUBSTITUTED("被替换", 1, "BE_SUBSTITUTED"),
        ENABLE("启用", 2, "ENABLE");
        private String name;
        private int value;
        private String code;

        private File_STATUS_ENUM(String name, int value, String code) {
            this.name = name;
            this.value = value;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    //文件操作枚举
    public static enum File_OPERATION_ENUM {
        DELETE("删除", 0, "DELETE"),
        DOWNLOAD("下载", 1, "DOWNLOAD"),
        UPLOAD("上传", 2, "UPLOAD"),
        PREVIEW("预览", 3, "PREVIEW"),
        SIGN("刷新凭证",4, "SIGN");
        private String name;
        private int value;
        private String code;

        private File_OPERATION_ENUM(String name, int value, String code) {
            this.name = name;
            this.value = value;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }


}
