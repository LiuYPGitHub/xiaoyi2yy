package com.ruoyi.web.controller.blog.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description：动态
 * @Author：kk
 * @Date：2019/9/18 10:52
 */
@Data
public class DynamicDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private String dynamicId;
    private String dynaContent;
    private String realName;
    private String nickName;
    private String userType;
    private String releaseTime;
    private List<String> dynaImgs;
    private String createBy;
    private Date createTime;
    private Date updateTime;
}
