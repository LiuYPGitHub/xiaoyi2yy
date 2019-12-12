package com.ruoyi.web.controller.blog.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动/案例表
 */
@Getter
@Setter
@ToString
public class Case implements Serializable {

    private static final long serialVersionUID = 1L;

    // 活动id
    private String evtId;
    // 用户id
    private String userId;
    // 活动标题
    private String evtTitle;
    // 活动类型[0:体验课 1:讲座 2:参访 3:游园 4:研学 5:其他]
    private String evtType;
    // 活动方式 [0:线下 1:线上]Status
    private String evtWay;
    // 活动开始时间
//    @JsonFormat(timezone = "GMT",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date evtStartTime;
    // 活动结束时间
//    @JsonFormat(timezone = "GMT",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date evtEndTime;
    // 活动地址
    private String evtAddress;
    // 活动内容
    private String evtContent;
    // 活动图片
    private String evtImgUrl;
    // 活动状态 [0:正常 1:草稿箱 2:案例][当前时间>活动结束时间 为案例状态]
    private String evtStatus;
    // 审核状态 [0:通过 1:待审核 2:未通过]
    private String evtCheckStatus;
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
    // 活动链接地址
    private String evtCoDdress;

    private String userType;

    private Date evtRelTime;
    //
    private String evtRelName;
}
