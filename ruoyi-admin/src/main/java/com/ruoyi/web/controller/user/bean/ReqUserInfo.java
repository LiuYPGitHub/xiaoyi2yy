package com.ruoyi.web.controller.user.bean;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Description：用户管理
 * @Author：kk
 * @Date：2019/9/9 17:02
 */
@Data
public class ReqUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String realName;
    @Pattern(regexp = "^1[\\d]{10}", message = "手机号格式不正确")
    private String telNum;
    private String userType;
    private String nickName;
    private String contacts;
    private String labels;

}
