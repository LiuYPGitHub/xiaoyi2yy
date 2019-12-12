package com.ruoyi.web.controller.user.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description：leads机构
 * @Author：kk
 * @Date：2019/10/14 09:56
 */
@Data
@ToString
public class OperatorBean implements Serializable {

    private String userId;
    private String userInfoId;
    private String orgId;
    private String orgName;
    private String realName;
    private String telNum;
    private String password;
    private String userState;
    private String userType;
    private String role;
    private String isDelete;
    private String platform;
}
