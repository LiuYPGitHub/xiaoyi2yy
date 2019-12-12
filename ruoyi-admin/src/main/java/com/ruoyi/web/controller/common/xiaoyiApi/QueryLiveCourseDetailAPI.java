package com.ruoyi.web.controller.common.xiaoyiApi;

import com.ruoyi.web.controller.common.xiaoyiApi.bean.APICourseDetail;

/**
 * 添翼申学-查询直播课程详情接口
 * @author CJ
 * @date 2019/10/12
 */
public interface QueryLiveCourseDetailAPI {

    QueryLiveCourseDetailResponse process(QueryLiveCourseDetailRequest request);

    void checkParms(QueryLiveCourseDetailRequest request);

    APICourseDetail processJson(String json);

}
