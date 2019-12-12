package com.ruoyi.web.controller.common.videoApi;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 保利威根据频道编号开始推流返回
 * @date 2019/11/27
 */
@ToString
@Getter
@Setter
public class StartPistonFlowResponse extends PistonFlowResponse implements Serializable {

    private static final long serialVersionUID = 5025127029353308948L;
    @ApiModelProperty(value = "停止推流结果信息",notes = "停止推流结果信息",required = true)
    private String data;

}
