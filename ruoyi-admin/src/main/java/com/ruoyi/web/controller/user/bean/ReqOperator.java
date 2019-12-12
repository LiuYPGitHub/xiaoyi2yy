package com.ruoyi.web.controller.user.bean;

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
public class ReqOperator implements Serializable {

    private String userId;
    private String telNum;
    private String operatorId;
    private String operatorName;
    private String orgId;
    private String password;
    private String status;
    private String ids;

}
