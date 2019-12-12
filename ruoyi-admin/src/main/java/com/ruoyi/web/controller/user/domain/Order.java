package com.ruoyi.web.controller.user.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Description：用户订单
 * @Author：kk
 * @Date：2019/10/28 10:35
 */
@Data
@ToString
public class Order extends BaseEntity {

    private String orderId;
    @Excel(name = "订单名称")
    private String orderName;

    @Excel(name = "商品订单号")
    private String orderNum;

    @Excel(name = "商品类型")
    private String orderType;

    @Excel(name = "订单渠道号")
    private String orderChannel;

    @Excel(name = "机构名")
    private String siteName;

    private String orgCode;

    @Excel(name = "渠道")
    private String channel;

    @Excel(name = "渠道名称")
    private String channelName;

    @Excel(name = "课程名称")
    private String courseName;

    @Excel(name = "课程类型")
    private String classType;

    @Excel(name = "用户名")
    private String userName;

    @Excel(name = "用户手机号")
    private String userMobile;

    @Excel(name = "交易订单号")
    private String payOrderNum;

    @Excel(name = "支付方式")
    private String payType;

    @Excel(name = "支付状态")
    private String payStatus;

    @Excel(name = "支付金额")
    private String cost;

    @Excel(name = "原始费用")
    private String primeCost;

    @Excel(name = "退款状态")
    private String refundName;

    @Excel(name = "退款时间")
    private String refundDate;

    @Excel(name = "退款金额")
    private String refundFee;

    @Excel(name = "订单完成时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date orderFinishDate;

    @Excel(name = "订单过期时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date orderExpireDate;

    private Date createDate;

}
