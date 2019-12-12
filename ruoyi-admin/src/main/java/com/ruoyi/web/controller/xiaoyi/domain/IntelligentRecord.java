package com.ruoyi.web.controller.xiaoyi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author CJ
 * @date 2019/11/4
 */
@ToString
@Getter
@Setter
public class IntelligentRecord implements Serializable {

    private static final long serialVersionUID = 6177299376687198490L;

    private Integer id;
    private Date createdAt;
    private String orgCode;
    //课程状态(1待审核 2未通过 3已下架 4已上架)
    private Integer status;
    private String code;
    private String courseName;
    private String teacherName;
    private String teacherInfo;
    private String desc;
    private Integer courseNum;
    private Integer courseMins;
    //课程适用学生
    private String classInfo;
    //是否删除
    private Integer isDel;
    //是否显示(1显示 0不显示)
    private Integer isShow;
    //课程创建日期
    private java.util.Date createDate;
    //课程开始日期
    private java.util.Date startDate;
    //课程结束日期
    private java.util.Date endDate;
    private java.util.Date updatedAt;
    private String createMobile;
    //学生数
    private String studentNum;
    //分类编号
    private String cataId;
    //保利文件名称
    private String polyTitle;

    private String vid;
    private String fileSize;
    //课程有效期(单位:天)
    private String duration;
    private String sessionIds;
    private String fileId;
    private String channelId;
    private String mp4;
    private String m3u8;
    private Integer isRevised;
    private String cover;

    private String siteName;
    private String createName;
    private Integer orderByType;
    private Integer videoStatus;

}
