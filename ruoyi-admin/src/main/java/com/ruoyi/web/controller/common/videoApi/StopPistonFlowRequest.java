package com.ruoyi.web.controller.common.videoApi;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 保利威根据频道编号停止推流入参
 * @author CJ
 * @date 2019/11/27
 */
@ToString
@Getter
@Setter
public class StopPistonFlowRequest extends PistonFlowRequest implements Serializable {

    private static final long serialVersionUID = -3772794467339928449L;

    public StopPistonFlowRequest() { }

    public StopPistonFlowRequest(String channelId) {
        super.setChannelId(channelId);
    }

}
