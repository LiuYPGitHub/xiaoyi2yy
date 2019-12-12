package com.ruoyi.web.controller.blog.domain;

import com.ruoyi.common.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class Leads implements Serializable {
    private static final long serialVersionUID = -4651120048702014395L;

    // id
    private String leId;
    // 机构名称（真实姓名）
    private String realName;
    // 家长姓名
    @Excel(name = "家长姓名")
    @NotNull(message = "姓名不能为空")
    private String leParName;
    // 家长电话
    @Excel(name = "家长电话")
    private String leParPhone;
    // 孩子姓名
    @Excel(name = "孩子姓名")
    private String leChiName;
    // 孩子年龄
    @Excel(name = "孩子年龄")
    private String leChiAge;
    // 就读学校名称
    @Excel(name = "就读学校名称")
    private String leSchool;
    // 所在城市（省市区？）
    @Excel(name = "所在城市（省市区？）")
    private String city;
    // 意向课程类型
   @Excel(name = "意向课程类型")
    private String leIntenType;
//    // leads来源
//    private String leSource;
    // [1:正常 2:删除]
    private String isDelete;
    // 创建者
    private String createBy;
    // 创建时间
//    @JsonFormat(timezone = "GMT",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    // 更新者
    private String updateBy;
    // 更新时间
//    @JsonFormat(timezone = "GMT",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    // 孩子性别
    private String leChiGen;
    // 就读年级
    private String grade;
    // 导入城市
    private String cityInput;


    // 地域编码
    private String areaCode;
    // 地域名
    private String areaName;
    // 地域父节点编码
    private String areaParentCode;
    // 地域级别
    private String areaLevel;
    // 0:无效，1:有效
    private String areaFlag;

    private String sheng;
    private String shi;
    private String qu;
}
