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
public class AddLiveChannelRequest implements Serializable {

    private static final long serialVersionUID = -3466648895157976939L;

    @ApiModelProperty(value = "频道名称",notes = "频道名称",required = true)
    private String name;
    @ApiModelProperty(value = "频道密码",notes = "频道密码",required = false)
    private String pwd;

}
