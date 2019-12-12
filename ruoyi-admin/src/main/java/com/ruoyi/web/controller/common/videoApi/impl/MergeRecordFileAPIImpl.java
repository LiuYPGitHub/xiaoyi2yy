package com.ruoyi.web.controller.common.videoApi.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.web.controller.common.constant.APIConstant;
import com.ruoyi.web.controller.common.constant.ExceptionConstant;
import com.ruoyi.web.controller.common.exception.ServiceException;
import com.ruoyi.web.controller.common.util.HttpClientUtil;
import com.ruoyi.web.controller.common.util.MD5HexUtil;
import com.ruoyi.web.controller.common.videoApi.MergeRecordFileAPI;
import com.ruoyi.web.controller.common.videoApi.MergeRecordFileRequest;
import com.ruoyi.web.controller.common.videoApi.MergeRecordFileResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CJ
 * @date 2019/10/23
 */
@Component
public class MergeRecordFileAPIImpl implements MergeRecordFileAPI {

    @Override
    public MergeRecordFileResponse process(MergeRecordFileRequest request) {
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
        if(!StringUtils.isEmpty(request.getAutoConvert())){
            map.put("autoConvert",request.getAutoConvert());
        }
        if(!StringUtils.isEmpty(request.getMergeMp4())){
            //y
            map.put("mergeMp4",request.getMergeMp4());
        }
        map.put("sign", MD5HexUtil.Ksort(map));
        String url="http://api.polyv.net/live/v3/channel/record/merge";
        String json = HttpClientUtil.doPost(url, map);
        return processJson(json);
    }

    @Override
    public MergeRecordFileResponse processJson(String json) {
        MergeRecordFileResponse response=new MergeRecordFileResponse();
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
    public void checkParms(MergeRecordFileRequest request) {
        if(StringUtils.isEmpty(request)){
            throw new ServiceException(ExceptionConstant.EXCEPTION_PARMSISNULL,"参数为空");
        }else if(StringUtils.isEmpty(request.getChannelId())){
            throw new ServiceException(ExceptionConstant.EXCEPTION_PARMSISNULL,"渠道编号参数为空");
        }else if(StringUtils.isEmpty(request.getFileIds())){
            throw new ServiceException(ExceptionConstant.EXCEPTION_PARMSISNULL,"文件编号参数为空");
        }/*else if(request.getFileIds().size()<=0){
            throw new ServiceException(ExceptionConstant.EXCEPTION_PARMSISNULL,"文件编号参数为空");
        }*/
    }

}
