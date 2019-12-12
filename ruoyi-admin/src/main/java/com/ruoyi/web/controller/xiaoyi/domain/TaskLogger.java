package com.ruoyi.web.controller.xiaoyi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author CJ
 * @date 2019/10/15
 */
@ToString
@Getter
@Setter
public class TaskLogger implements Serializable {

    private static final long serialVersionUID = -7922938588022855170L;

    private Integer id;
    private String taskName;
    private String result;
    private String args;
    private String error;
    private Date updatedAt;
    private Date createdAt;
    private String orgCode;

}
