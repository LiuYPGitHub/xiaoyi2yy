package com.ruoyi.web.controller.report.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Report form
 */
@Getter
@Setter
@ToString
public class Cour implements Serializable {

    private static final long serialVersionUID = -8663308572758602243L;

    // 课程 id
    private String id;
    // 课程code（必须唯一且和添翼平台现有不能重复）
    private String code;
    // 点击量
    private String PV;
    // 访问量
    private String UV;
    // 1:未删除 2:已删除
    private String isDel;
    // 1:展示 2:不展示
    private String isShow;
    // 1待审核 2未通过 3已下架 4已上架 5 已通过
    private String status;
    // 课程创建日期
    private Date regDate;
    // 课程开始日期
    private Date startDate;
    // 课程结束日期
    private Date endDate;
    // 更新时间
    private Date updatedAt;
    //
    private String courseName;
}
//    // 课程平台id
//    private String classId;
//    // 课程名称
//    private String courseName;
//    // 课程价格
//    private String price;
//    // 教师名称
//    private String teacherName;
//    // 教师天翼平台id
//    private String teacherId;
//    // 教师资料
//    private String teacherInfo;