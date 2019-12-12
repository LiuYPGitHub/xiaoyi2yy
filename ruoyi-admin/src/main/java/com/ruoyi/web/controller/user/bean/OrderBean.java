package com.ruoyi.web.controller.user.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description：用户订单
 * @Author：kk
 * @Date：2019/10/28 10:35
 */
@Data
public class OrderBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String orderId;
    private String userName;
    private String userMobile;
    private String courseName;
    private String channelName;
    private String siteName;
    private String channel;
    private String payStatus;
    private String beginTime;
    private String endTime;
    private String orderBy;

}
