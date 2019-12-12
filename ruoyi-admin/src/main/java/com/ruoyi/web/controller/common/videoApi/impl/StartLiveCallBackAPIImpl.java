package com.ruoyi.web.controller.common.videoApi.impl;

import com.ruoyi.web.controller.common.videoApi.StartLiveCallBackAPI;
import com.ruoyi.web.controller.common.videoApi.StartLiveCallBackRequest;
import org.springframework.stereotype.Component;

/**
 * @author CJ
 * @date 2019/10/31
 */
@Component
public class StartLiveCallBackAPIImpl implements StartLiveCallBackAPI {
    @Override
    public StartLiveCallBackRequest process(StartLiveCallBackRequest request) {
        return request;
    }
}
