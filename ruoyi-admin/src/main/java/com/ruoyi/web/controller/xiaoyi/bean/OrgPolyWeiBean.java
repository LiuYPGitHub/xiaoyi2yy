package com.ruoyi.web.controller.xiaoyi.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author CJ
 * @date 2019/11/1
 */
@ToString
@Getter
@Setter
public class OrgPolyWeiBean implements Serializable {

    private static final long serialVersionUID = -584757433434404528L;

    private String orgId;
    private String orgName;

}
