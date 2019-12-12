package com.ruoyi.web.controller.user.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description：用户管理
 * @Author：KKKK
 * @Date：2019/9/9 17:02
 */
@Data
public class ReqLeads implements Serializable {
    private static final long serialVersionUID = 1L;

    private String telNum;
    private String password;
    private String realName;
    private String nickName;
    private String userType;


}
