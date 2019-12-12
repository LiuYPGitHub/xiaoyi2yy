package com.ruoyi.web.controller.blog.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 小平_字典表
 */
@Getter
@Setter
@ToString
public class Dict implements Serializable {
    private static final long serialVersionUID = 162380532847599662L;
    // id
    private String codeId;
    // 标记
    private String codeType;
    // 数字
    private String codeNo;
    // 名称
    private String codeName;
    // 状态[1:有效，2:无效，]
    private String codeFlay;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
}
