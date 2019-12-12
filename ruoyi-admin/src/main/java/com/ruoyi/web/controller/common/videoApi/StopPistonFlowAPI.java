package com.ruoyi.web.controller.common.videoApi;
/**
 * 保利威根据频道编号停止推流
 * @author CJ
 * @date 2019/11/27
 */
public interface StopPistonFlowAPI {

    StopPistonFlowResponse process(StopPistonFlowRequest request);

    StopPistonFlowResponse processJson(String json);

}
