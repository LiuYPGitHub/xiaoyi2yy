package com.ruoyi.web.controller.common.videoApi.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.web.controller.common.constant.APIConstant;
import com.ruoyi.web.controller.common.constant.ExceptionConstant;
import com.ruoyi.web.controller.common.exception.ServiceException;
import com.ruoyi.web.controller.common.util.HttpClientUtil;
import com.ruoyi.web.controller.common.util.MD5HexUtil;
import com.ruoyi.web.controller.common.videoApi.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CJ
 * @date 2019/10/31
 */
@Slf4j
@Component
public class SettingLiveCallBackAPIImpl extends BaseCheck implements SettingLiveCallBackAPI {

    @Override
    public SettingLiveCallBackResponse process(SettingLiveCallBackRequest request) {
        Map<String,String> map=new HashMap<>();
        map.put("appId", APIConstant.API_APP_ID);
        map.put("timestamp",String.valueOf(Calendar.getInstance().getTimeInMillis()));
        if(!StringUtils.isEmpty(request.getUrl())){
            map.put("url",request.getUrl());
        }
        map.put("sign", MD5HexUtil.Ksort(map));
        String url="http://api.polyv.net/live/v2/user/"+APIConstant.API_USER_ID+"/set-record-callback";
        String json = HttpClientUtil.doPost(url, map);
        log.info("设置录制回调通知url结果:"+json);
        return processJson(json);
    }

    @Override
    public SettingLiveCallBackResponse processJson(String json) {
        checkJson(json);
        JSONObject object = JSONObject.parseObject(json);
        Object code = object.get("code");
        if(!org.springframework.util.StringUtils.isEmpty(code) && "200".equals(code.toString())){
            return new SettingLiveCallBackResponse(true);
        }else{
            throw new ServiceException(ExceptionConstant.EXCEPTION_JSON_ERROR," json:"+json);
        }
    }
}
