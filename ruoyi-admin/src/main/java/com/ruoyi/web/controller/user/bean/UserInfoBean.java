package com.ruoyi.web.controller.user.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description：用户管理
 * @Author：kk
 * @Date：2019/9/9 17:02
 */
@Data
@ToString
public class UserInfoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    private String telNum;
    private String userType;
    private String userTypeName;
    private String userState;
    private String userStateName;
    private String password;
    private String isDelete;

    private String token;

    private String realName;
    private String userInfoId;
    private String nickName;
    private String contacts;
    private String headImg;
    @JsonFormat(timezone = "GMT", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    private String strBirthday;
    private String edu;
    private String labels;
    private String city;
    private String org;
    private String personalInfo;
    private String personalBG;
    private List<ResumeBean> resume;
    @JsonFormat(timezone = "GMT", pattern = "yyyy-MM-dd HH:mm")
    private String createTime;
    @JsonFormat(timezone = "GMT", pattern = "yyyy-MM-dd HH:mm")
    private String updateTime;

}
