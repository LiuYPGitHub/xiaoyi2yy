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
public class ResOperator implements Serializable {

    private String orgId;

    @Excel(name = "机构名称")
    private String orgName;

    @Excel(name = "运营人员姓名")
    private String operatorName;

    @Excel(name = "手机号码")
    private String telNum;

    @Excel(name = "管理权限", readConverterExp = "1=是,2=否")
    private String role;

    @Excel(name = "启禁用", readConverterExp = "1=启用,1=禁用")
    private String userState;

}
