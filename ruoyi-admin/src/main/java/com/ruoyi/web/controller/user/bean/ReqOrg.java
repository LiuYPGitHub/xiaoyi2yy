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
public class ReqOrg implements Serializable {

    private String telNum;
    private String orgId;
    private String userType;
    private String orgName;
    private String campus;
    private String userState;
    private String contacts;
    private String headImgUrl;
    private String phone;
    private String info;
    private String platform;
    private String address;

}
