package com.ruoyi.web.controller.xiaoyi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author CJ
 * @date 2019/11/1
 */
@ToString
@Getter
@Setter
public class OrgPolyWei implements Serializable {

    private static final long serialVersionUID = -5296288653766562540L;

    private Integer id;
    private String orgId;
    private String orgName;
    private String channelId;
    private String cataId;
    private Integer isDel;
    private Integer isFlag;
    private Date createTime;

    //频道信息
    //频道名称
    private String channelName;
    //频道密码
    private String channelPwd;

    //视频分类信息
    private String cataName;
    private String parentId;

}
