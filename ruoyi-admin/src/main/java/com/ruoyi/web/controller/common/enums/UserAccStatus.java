package com.ruoyi.web.controller.common.enums;

import com.ruoyi.common.utils.StringUtils;

/**
 * 用户状态
 *
 * @author kk
 */
public enum UserAccStatus {
    ENABLE("1", "正常"), DELETE("2", "冻结");

    private final String code;
    private final String info;


    UserAccStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public static String getInfo(String code) {
        for (UserAccStatus cooperationStatus : UserAccStatus.values()) {
            if (StringUtils.equals(code, cooperationStatus.getCode())) {
                return cooperationStatus.info;
            }
        }

        return null;
    }

}
