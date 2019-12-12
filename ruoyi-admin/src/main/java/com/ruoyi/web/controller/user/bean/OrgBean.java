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
public class OrgBean implements Serializable {

    private String userId;
    private String userInfoId;
    private String orgId;
    private String telNum;
    private String userState;
    private String userType;
    private String realName;
    private String nickName;
    private String password;
    private String campus;
    private String role;
    private String isDelete;
    private String contacts;
    private String headImgUrl;
    private String phone;
    private String personalInfo;
    private String platform;
    private String address;

}
