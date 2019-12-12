package com.ruoyi.web.controller.user.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description：省市区
 * @Author：kk
 * @Date：2019/9/27 16:28
 */
@Data
@ToString
public class AreaCodeBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String areaCode;
    private String areaName;
    private String areaPCode;
}
