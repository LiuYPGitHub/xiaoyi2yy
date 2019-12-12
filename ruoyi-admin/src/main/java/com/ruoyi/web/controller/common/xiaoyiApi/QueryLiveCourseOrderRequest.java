package com.ruoyi.web.controller.common.xiaoyiApi;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author CJ
 * @date 2019/10/14
 */
@ToString
@Getter
@Setter
public class QueryLiveCourseOrderRequest implements Serializable {

    private static final long serialVersionUID = -1952529502421503131L;

    @ApiModelProperty(value = "课程编号",notes = "课程编号",required = true)
    private String classId;

}
