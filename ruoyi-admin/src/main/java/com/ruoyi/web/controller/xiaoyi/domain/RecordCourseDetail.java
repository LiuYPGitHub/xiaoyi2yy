package com.ruoyi.web.controller.xiaoyi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author CJ
 * @date 2019/11/5
 */
@ToString
@Getter
@Setter
public class RecordCourseDetail implements Serializable {

    private static final long serialVersionUID = -2009426713491210474L;

    private Integer id;
    private Integer courseId;
    private String lessonId;
    private String lessonName;
    private String code;
    private String orgCode;

    private String vid;
    private String mp4;
    private Integer recordHour;

    private Date lessonDate;
    private Date createdAt;
    private Date updatedAt;
    //试看 1 允许 2 不允许
    private Integer allowTaste;

}
