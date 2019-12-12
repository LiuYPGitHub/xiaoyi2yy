package com.ruoyi.web.controller.common.videoApi.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.web.controller.common.constant.APIConstant;
import com.ruoyi.web.controller.common.util.HttpClientUtil;
import com.ruoyi.web.controller.common.util.MD5HexUtil;
import com.ruoyi.web.controller.common.videoApi.StartPistonFlowAPI;
import com.ruoyi.web.controller.common.videoApi.StartPistonFlowRequest;
import com.ruoyi.web.controller.common.videoApi.StartPistonFlowResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 保利威根据频道编号开始/恢复推流实现
 * @author CJ
 * @date 2019/11/27
 */
@Component
@Slf4j
public class StartPistonFlowAPIImpl implements StartPistonFlowAPI {

    @Override
    public StartPistonFlowResponse process(StartPistonFlowRequest request) {
        String channelId = request.getChannelId();
        Map<String,String> map=new HashMap<>();
        String ptime = String.valueOf(Calendar.getInstance().getTimeInMillis());
        map.put("appId", APIConstant.API_APP_ID);
        map.put("timestamp",ptime);
        map.put("userId",APIConstant.API_USER_ID);
        map.put("sign",MD5HexUtil.Ksort(map));
        String url = "http://api.polyv.net/live/v2/stream/"+channelId+"/resume";
        String json = HttpClientUtil.doPost(url, map);
        log.info("根据频道编号开始/恢复推流实现结果:" + json);
        return processJson(json);
    }

    @Override
    public StartPistonFlowResponse processJson(String json) {
        StartPistonFlowResponse response=new StartPistonFlowResponse();
        JSONObject jsonObject = JSONObject.parseObject(json);
        response.setCode(jsonObject.getString("code"));
        response.setStatus(jsonObject.getString("status"));
        response.setMessage(jsonObject.getString("message"));
        response.setData(jsonObject.getString("data"));
        return response;
    }

}
