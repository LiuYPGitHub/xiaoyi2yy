package com.ruoyi.web.controller.xiaoyi.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

/**
 * @author CJ
 * @date 2019/11/27
 */
@ToString
@Getter
@Setter
public class IntelligentRecordBean implements Serializable {

    private static final long serialVersionUID = 3179808956117624309L;
    //智能录播编号
    private Integer id;
    //智能录播所属频道
    private String channelId;
    //智能录播课程开始录制时间
    private Date startDate;
    //智能录播课程开始结束时间
    private Date endDate;
    //推流状态 1:开启 2:关闭
    private Integer flowState;

}
