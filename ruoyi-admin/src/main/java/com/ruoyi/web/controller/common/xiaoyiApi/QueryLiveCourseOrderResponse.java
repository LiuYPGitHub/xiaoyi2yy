package com.ruoyi.web.controller.common.xiaoyiApi;

import com.ruoyi.web.controller.common.videoApi.BaseResponse;
import com.ruoyi.web.controller.common.xiaoyiApi.bean.APICourseOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author CJ
 * @date 2019/10/14
 */
@ToString
@Getter
@Setter
public class QueryLiveCourseOrderResponse extends BaseResponse implements Serializable {
    private static final long serialVersionUID = -4737179774728852549L;

    public QueryLiveCourseOrderResponse(List<APICourseOrder> orders) {
        success();
        this.orders = orders;
    }

    @ApiModelProperty(value ="课程订单信息",notes = "课程订单信息",required = true)
    private List<APICourseOrder> orders;

}
