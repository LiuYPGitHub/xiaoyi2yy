package com.ruoyi.web.controller.user.bean;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CooperationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cooperationId;
    private String orgId;
    @Excel(name = "机构名称或真实姓名")
    private String orgName;
    @Excel(name = "联系电话")
    private String orgTel;
    @Excel(name = "联系人")
    private String contacts;
    @Excel(name = "性别")
    private String sex;
    private String type;
    @Excel(name = "合作对象")
    private String typeName;
    private String cooperationObjId;
    @Excel(name = "合作对象真实名字")
    private String cooperationObjName;
    @Excel(name = "昵称")
    private String cooperationObjNickName;
    @Excel(name = "需求简介")
    private String info;
    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String createTime;
    @Excel(name = "修改时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String updateTime;


}
