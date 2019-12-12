package com.ruoyi.web.controller.blog.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description：动态
 * @Author：kk
 * @Date：2019/9/18 10:52
 */
@Data
public class DynamicBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String dynamicId;
    private String dynaContent;
    private String realName;
    private String nickName;
    private String userType;
    private String releaseTime;
    private String dynaImg;
    private String createBy;
    private Date createTime;
    private Date updateTime;
}
