package com.ruoyi.web.controller.common.videoApi;
/**
 * 保利威根据频道编号开始/恢复推流
 * @author CJ
 * @date 2019/11/27
 */
public interface StartPistonFlowAPI {

    StartPistonFlowResponse process(StartPistonFlowRequest request);

    StartPistonFlowResponse processJson(String json);

}
