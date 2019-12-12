package com.ruoyi.web.controller.common.xiaoyiApi;

import com.ruoyi.web.controller.common.xiaoyiApi.bean.APICourseOrder;

import java.util.List;

/**
 * 添翼申学-查询直播课程订单接口
 * @author CJ
 * @date 2019/10/14
 */
public interface QueryLiveCourseOrderAPI {

    QueryLiveCourseOrderResponse process(QueryLiveCourseOrderRequest request);

    void checkParms(QueryLiveCourseOrderRequest request);

    List<APICourseOrder> processJson(String json);

}
