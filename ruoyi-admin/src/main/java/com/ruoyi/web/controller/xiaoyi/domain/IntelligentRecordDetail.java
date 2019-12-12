package com.ruoyi.web.controller.xiaoyi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author CJ
 * @date 2019/11/4
 */
@ToString
@Getter
@Setter
public class IntelligentRecordDetail implements Serializable {

    private static final long serialVersionUID = 403897598419200162L;

    private Integer id;
    private String courseId;
    private String lessonId;
    private String lessonName;
    private String code;
    private String orgCode;
    private String vid;
    private String mp4;
    private Integer recordHour;
    private String createdAt;
    private String updatedAt;
    //1允许试看 2不允许
    private String allowTaste;

}
