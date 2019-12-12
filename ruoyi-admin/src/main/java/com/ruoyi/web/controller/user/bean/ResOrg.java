package com.ruoyi.web.controller.user.bean;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description：leads机构
 * @Author：kk
 * @Date：2019/10/14 09:56
 */
@Data
@ToString
public class ResOrg implements Serializable {

    private String orgId;
    @Excel(name = "机构姓名")
    private String orgName;
    private String headImgUrl;
    @Excel(name = "联系人")
    private String contacts;
    @Excel(name = "联系人电话")
    private String phone;
    @Excel(name = "校区")
    private String campus;
    @Excel(name = "地址")
    private String address;
    @Excel(name = "描述")
    private String info;
    @Excel(name = "启禁用", readConverterExp = "1=启用,2=禁用")
    private String userState;
    @Excel(name = "运营人数")
    private Integer operatorCount;





}
