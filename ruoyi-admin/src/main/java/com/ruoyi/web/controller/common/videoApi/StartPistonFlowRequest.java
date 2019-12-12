package com.ruoyi.web.controller.common.videoApi;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 保利威根据频道编号开始推流入参
 * @author CJ
 * @date 2019/11/27
 */
@ToString
@Getter
@Setter
public class StartPistonFlowRequest extends PistonFlowRequest implements Serializable {

    private static final long serialVersionUID = -3772794467339928449L;

    public StartPistonFlowRequest() { }

    public StartPistonFlowRequest(String channelId) {
        super.setChannelId(channelId);
    }

}
