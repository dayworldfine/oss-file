package com.oss.tool.util;

/**
 * @ClassName：ConvertScaleUtil
 * @Description: 10进制64位转换
 * @Author：13738700108
 * @Data 2020/11/20 0:19
 * @Version: v1.0
 **/
public class ConvertScaleUtil {
    static final char[] digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '+', '/'};

    public ConvertScaleUtil() {
    }

    public static String get10to64(long number) {
        char[] buf = new char[64];
        int charPos = 64;
        int radix = 64;
        long mask = (long)(radix - 1);

        do {
            --charPos;
            buf[charPos] = digits[(int)(number & mask)];
            number >>>= 6;
        } while(number != 0L);

        return new String(buf, charPos, 64 - charPos);
    }

    public static long get64to10(String decompStr) {
        long result = 0L;

        for(int i = decompStr.length() - 1; i >= 0; --i) {
            for(int j = 0; j < digits.length; ++j) {
                if (decompStr.charAt(i) == digits[j]) {
                    result += (long)j << 6 * (decompStr.length() - 1 - i);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String to64 = ConvertScaleUtil.get10to64(SnowUtil.generateId());
        System.out.println(to64);
    }
}
