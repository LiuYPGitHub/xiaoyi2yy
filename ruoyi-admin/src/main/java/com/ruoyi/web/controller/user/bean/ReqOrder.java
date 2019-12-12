package com.ruoyi.web.controller.user.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description：用户订单
 * @Author：kk
 * @Date：2019/10/28 10:35
 */
@Data
public class ReqOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    private String orderId;
    private String userName;
    private String userMobile;
    private String courseName;
    private String siteName;
    private String channelName;
    private String channel;
    private String beginTime;
    private String endTime;
    private String payStatus;
    private Date orderFinishDate;
    private String orderBy;

}
