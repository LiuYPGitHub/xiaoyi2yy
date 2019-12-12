package com.ruoyi.web.controller.common.videoApi;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 保利威恢复、开始推流接口入参
 * @author CJ
 * @date 2019/11/27
 */
@ToString
@Getter
@Setter
public class PistonFlowRequest extends BaseCheck implements Serializable {

    private static final long serialVersionUID = -6635305147791148277L;

    @ApiModelProperty(value = "保利威频道编号",notes = "保利威频道编号",required = true)
    private String channelId;

}
