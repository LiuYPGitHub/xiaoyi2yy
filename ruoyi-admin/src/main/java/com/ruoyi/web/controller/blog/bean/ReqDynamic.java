package com.ruoyi.web.controller.blog.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description：动态
 * @Author：kk
 * @Date：2019/9/18 10:52
 */
@Data
public class ReqDynamic implements Serializable {
    private static final long serialVersionUID = 1L;

    private String realName;
    private String dynaContent;

}
