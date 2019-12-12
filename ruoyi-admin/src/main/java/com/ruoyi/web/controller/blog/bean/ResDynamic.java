package com.ruoyi.web.controller.blog.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description：动态
 * @Author：kk
 * @Date：2019/9/18 10:52
 */
@Data
public class ResDynamic implements Serializable {
    private static final long serialVersionUID = 1L;
    private String dynamicId;
    private String dynaContent;
    private String realName;
    private String nickName;
    private String userType;
    private String releaseTime;

}
