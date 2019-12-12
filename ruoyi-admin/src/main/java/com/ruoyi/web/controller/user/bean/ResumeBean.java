package com.ruoyi.web.controller.user.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description：用户管理
 * @Author：kk
 * @Date：2019/9/9 17:02
 */
@Data
@ToString
public class ResumeBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String resumeId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    private String strStartDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String strEndDate;
    private String company;
    private String title;
}
