package com.ruoyi.web.controller.xiaoyi.utils;

/**
 * @author CJ
 * @date 2019/10/25
 */
public class CheckUtil {
    /**
     * 判断字符是否为全数字
     * @param str 字符
     * @return 判断结果
     */
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
