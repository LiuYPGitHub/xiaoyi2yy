package com.ruoyi.web.controller.common.enums;

import com.ruoyi.common.utils.StringUtils;

/**
 * 用户状态
 *
 * @author kk
 */
public enum UserTypeStatus {
    KOL("1", "KOL"), TEA("2", "名师"), ORG("3", "机构"), OTHER("4", "系统");

    private final String code;
    private final String info;


    UserTypeStatus(String code, String info) {
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
        for (UserTypeStatus cooperationStatus : UserTypeStatus.values()) {
            if (StringUtils.equals(code, cooperationStatus.getCode())) {
                return cooperationStatus.info;
            }
        }

        return null;
    }

}
