package com.ruoyi.web.controller.user.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 用户对象 lz_pf_user
 *
 * @author kk
 *
 */
@Data
@ToString
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String userId;

    private String password;

    private String telNum;

    private String userState;

    private String userType;

    private String isDelete;

    /**
     * 创建者
     */
    private String createBy;

    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    private Date updateTime;


}
