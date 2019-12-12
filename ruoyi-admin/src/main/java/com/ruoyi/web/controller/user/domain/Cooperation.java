package com.ruoyi.web.controller.user.domain;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Cooperation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name = "合作Id")
    private String cooperationId;
    @Excel(name = "机构Id")
    private String orgId;
    @Excel(name = "机构")
    private String orgName;
    @Excel(name = "联系电话")
    private String orgTel;
    @Excel(name = "联系人")
    private String contacts;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "合作对象")
    private String type;
    private String cooperationObjId;

    private String cooperationObjState;
    @Excel(name = "需求简介")
    private String info;

    private String isDelete;

    /**
     * 创建者
     */
    private String createBy;

    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    private Date updateTime;


}
