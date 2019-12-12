package com.ruoyi.web.controller.common.enums;

import com.ruoyi.common.utils.StringUtils;

/**
 * 用户状态
 *
 * @author kk
 *
 */
public enum CooperationStatus {
    KOL("1", "KOL"), TEA("2", "名师"), ORG("3", "机构"), OTHER("4", "其他");

    private final String code;
    private final String info;


    CooperationStatus(String code, String info) {
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
        for (CooperationStatus cooperationStatus : CooperationStatus.values()) {
            if (StringUtils.equals(code,cooperationStatus.getCode())) {
                return cooperationStatus.info;
            }
        }

        return null;
    }

}
