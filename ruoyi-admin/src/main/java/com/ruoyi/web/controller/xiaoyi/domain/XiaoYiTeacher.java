package com.ruoyi.web.controller.xiaoyi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author CJ
 * @date 2019/10/23
 */
@ToString
@Getter
@Setter
public class XiaoYiTeacher implements Serializable {

    private static final long serialVersionUID = -5644328002101332634L;

    private String teacherId;
    private String phone;
    private String orgId;
    private String orgName;
    private String tyTeacherId;
    private String profession;
    private String platform;
    private String remarks;
    private String isDelete;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;

}
