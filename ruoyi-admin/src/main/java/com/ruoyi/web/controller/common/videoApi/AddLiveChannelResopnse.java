package com.ruoyi.web.controller.common.videoApi;

import com.ruoyi.web.controller.common.videoApi.bean.APILiveChannel;
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
public class AddLiveChannelResopnse extends BaseResponse implements Serializable {

    @ApiModelProperty(value = "新建的频道信息",notes = "新建的频道信息")
    private APILiveChannel channel;

}
