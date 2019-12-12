package com.ruoyi.web.controller.xiaoyi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author CJ
 * @date 2019/10/14
 */
@ToString
@Getter
@Setter
public class LiveCourse implements Serializable {

    private static final long serialVersionUID = -2300173957901982L;
    //课程编号
    private Integer id;
    //课程代码
    private String code;
    //课程平台编号
    private String classId;
    //课程名称
    private String courseName;
    //账户名称(手机号码)
    private String account;
    //教师平台编号
    private String teacherId;
    //教师名称
    private String teacherName;
    //教师资料
    private String teacherInfo;
    //课程描述
    private String desc;
    //课程类型
    private String classType;
    //课程标签
    private String classTag;
    //课程难度
    private String classDifficult;
    //课程封面
    private String coverImg;
    //课程主图
    private String courseIntroduceImg;
    //课时数
    private Integer courseNum;
    //课时
    private String classHour;
    //课程顾问
    private String courseConsultant;
    //温馨提示
    private String coursePrompt;
    //课程目标
    private String courseObj;
    //课程特色
    private String courseFeature;
    //课程内容
    private String courseContent;
    //课程有效期(单位:天)
    private String expirationDuration;
    //课程适用学生
    private String classInfo;
    //讲义/教材
    private String textBook;
    //课程售价
    private BigDecimal price;
    //点击量
    private Integer clickCount;
    //访问量
    private Integer visitCount;
    //跳出率
    private String totalBR;
    //是否删除
    private Integer isDel;
    //是否显示
    private Integer isShow;
    //是否显示课程评价
    private Integer showEvaluation;

    //课程状态(1待审核 2未通过 3已下架 4已上架)
    private Integer status;
    //课程创建日期
    private Date createDate;
    //课程开始日期
    private Date startDate;
    //课程结束日期
    private Date endDate;
    private Date updatedAt;
    private Date createdAt;
    private String orgCode;

    private String createMobile;
    private Date enrollStartDate;
    private Date enrollEndDate;

    private String viewType;
    private String teacherImgage;

    private String siteName;
    private String createName;
    private Integer orderByType;

}
