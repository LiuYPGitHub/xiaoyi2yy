package com.ruoyi.web.controller.common.videoApi;

import com.ruoyi.web.controller.common.videoApi.bean.APICata;
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
public class AddVideoCataResopnse extends BaseResponse implements Serializable {

    private static final long serialVersionUID = 1404529482997052692L;
    @ApiModelProperty(value = "新建之后的分类信息",notes = "新建之后的分类信息")
    private APICata cata;

}
