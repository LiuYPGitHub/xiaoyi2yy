package com.ruoyi.web.controller.common.videoApi;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 获取保利威视频播放token入参实体
 * @author CJ
 * @date 2019/11/25
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class QueryVideoTokenRequest implements Serializable {

    private static final long serialVersionUID = -3883163408953525134L;

    @ApiModelProperty(value = "保利威视频编号",notes = "保利威视频编号",required = true)
    private String vid;

}
