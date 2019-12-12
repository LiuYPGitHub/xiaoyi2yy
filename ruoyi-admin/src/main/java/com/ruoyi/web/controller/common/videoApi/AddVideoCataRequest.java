package com.ruoyi.web.controller.common.videoApi;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author CJ
 * @date 2019/12/3
 */
@ToString
@Getter
@Setter
public class AddVideoCataRequest implements Serializable {

    private static final long serialVersionUID = -3771367312448018045L;
    @ApiModelProperty(value = "分类名称",notes = "分类名称",required = true)
    private String cataName;
    @ApiModelProperty(value = "上级分类编号",notes = "上级分类编号",required = false)
    private String parentId;

}
