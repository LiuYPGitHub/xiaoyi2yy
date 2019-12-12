package com.ruoyi.web.controller.common.enums;

import com.ruoyi.common.utils.StringUtils;

/**
 * 用户状态
 *
 * @author kk
 *
 */
public enum SexStatus {
    MAN("1", "男"), Wo("2", "女"), OTHER("3", "其他");

    private final String code;
    private final String info;


    SexStatus(String code, String info) {
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
        for (SexStatus cooperationStatus : SexStatus.values()) {
            if (StringUtils.equals(code,cooperationStatus.getCode())) {
                return cooperationStatus.info;
            }
        }

        return null;
    }

}
