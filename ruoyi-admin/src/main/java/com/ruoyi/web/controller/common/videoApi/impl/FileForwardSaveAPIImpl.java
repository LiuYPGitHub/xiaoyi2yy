package com.ruoyi.web.controller.common.videoApi.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.web.controller.common.constant.APIConstant;
import com.ruoyi.web.controller.common.constant.ExceptionConstant;
import com.ruoyi.web.controller.common.exception.ServiceException;
import com.ruoyi.web.controller.common.util.HttpClientUtil;
import com.ruoyi.web.controller.common.util.MD5HexUtil;
import com.ruoyi.web.controller.common.videoApi.FileForwardSaveAPI;
import com.ruoyi.web.controller.common.videoApi.FileForwardSaveRequest;
import com.ruoyi.web.controller.common.videoApi.FileForwardSaveResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CJ
 * @date 2019/10/24
 */
@Slf4j
@Component
public class FileForwardSaveAPIImpl implements FileForwardSaveAPI {

    @Override
    public FileForwardSaveResponse process(FileForwardSaveRequest request) {
        checkParms(request);
        String channelId=request.getChannelId();
        StringBuffer file=new StringBuffer();
        for (String fileId : request.getFileIds()) {
            file.append(fileId+",");
        }
        String fileIds=file.toString();
        Map<String,String> map=new HashMap<>();
        map.put("appId", APIConstant.API_APP_ID);
        map.put("timestamp",APIConstant.API_TIMESTAMP_VALUE);
        map.put("channelId",channelId);
        map.put("fileIds",fileIds);
        if(!StringUtils.isEmpty(request.getCallbackUrl())){
            map.put("callbackUrl",request.getCallbackUrl());
        }
        String sign = MD5HexUtil.Ksort(map);
        map.put("sign",sign);
        String url="http://api.polyv.net/live/v3/channel/record/convert";
        String json = HttpClientUtil.doPost(url, map);
        log.info("异步批量转存录制文件到点播结果:"+json);
        return processJson(json);
    }

    @Override
    public FileForwardSaveResponse processJson(String json) {
        FileForwardSaveResponse response=new FileForwardSaveResponse();
        JSONObject object = JSONObject.parseObject(json);
        Object code = object.get("code");
        if(!StringUtils.isEmpty(code) && "200".equals(code.toString())){
            response.setCode(object.getString("code"));
            response.setStatus(object.getString("status"));
            response.setMessage(object.getString("message"));
            response.setData(object.getString("data"));
            return response;
        }else{
            throw new ServiceException(ExceptionConstant.EXCEPTION_JSON_ERROR," json:"+json);
        }
    }

    @Override
    public void checkParms(FileForwardSaveRequest request) {
        if(StringUtils.isEmpty(request)){
            throw new ServiceException(ExceptionConstant.EXCEPTION_PARMSISNULL,"参数为空");
        }else if(StringUtils.isEmpty(request.getChannelId())){
            throw new ServiceException(ExceptionConstant.EXCEPTION_PARMSISNULL,"渠道编号参数为空");
        }else if(StringUtils.isEmpty(request.getFileIds())){
            throw new ServiceException(ExceptionConstant.EXCEPTION_PARMSISNULL,"文件编号参数为空");
        }
    }

}
