package com.ruoyi.web.controller.common.videoApi.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.web.controller.common.constant.APIConstant;
import com.ruoyi.web.controller.common.constant.ExceptionConstant;
import com.ruoyi.web.controller.common.exception.ServiceException;
import com.ruoyi.web.controller.common.util.HttpClientUtil;
import com.ruoyi.web.controller.common.util.MD5HexUtil;
import com.ruoyi.web.controller.common.videoApi.BaseCheck;
import com.ruoyi.web.controller.common.videoApi.QueryVideoOneAPI;
import com.ruoyi.web.controller.common.videoApi.QueryVideoOneRequest;
import com.ruoyi.web.controller.common.videoApi.QueryVideoOneResponse;
import com.ruoyi.web.controller.common.videoApi.bean.APIRecordFile;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CJ
 * @date 2019/10/23
 */
@Component
public class QueryVideoOneAPIImpl extends BaseCheck implements QueryVideoOneAPI {

    @Override
    public QueryVideoOneResponse process(QueryVideoOneRequest request) throws NoSuchAlgorithmException {
        Map<String,String> map=new HashMap<>();
        map.put("appId", APIConstant.API_APP_ID);
        map.put("timestamp",String.valueOf(Calendar.getInstance().getTimeInMillis()));
        map.put("channelId",request.getChannelId());
        map.put("fileId",request.getFileId());
        String sign = MD5HexUtil.Ksort(map);
        map.put("sign",sign);
        String url="https://api.polyv.net/live/v3/channel/record/get";
        String json = HttpClientUtil.doGet(url, map);
        return processJson(json);
    }

    @Override
    public QueryVideoOneResponse processJson(String json) {
        checkJson(json);
        JSONObject object = JSONObject.parseObject(json);
        Object code = object.get("code");
        QueryVideoOneResponse response=new QueryVideoOneResponse();
        if(!StringUtils.isEmpty(code) && "200".equals(code.toString())){
            response.setCode(object.getString("code"));
            response.setStatus(object.getString("status"));
            response.setMessage(object.getString("message"));
            JSONObject jsonObject = JSONArray.parseObject(object.getString("data"));
            APIRecordFile file=new APIRecordFile();
            file.setDuration(jsonObject.getString("duration"));
            file.setFileName(jsonObject.getString("filename"));
            file.setFileSize(jsonObject.getString("filesize"));
            file.setUserId(jsonObject.getString("userId"));
            file.setFileId(jsonObject.getString("fileId"));
            file.setChannelSessionId(jsonObject.getString("channelSessionId"));
            file.setCreatedTime(jsonObject.getString("createdTime"));
            file.setStartTime(jsonObject.getString("startTime"));
            file.setEndTime(jsonObject.getString("endTime"));
            file.setBitrate(jsonObject.getString("bitrate"));
            file.setResolution(jsonObject.getString("resolution"));
            file.setChannelId(jsonObject.getString("channelId"));
            file.setHeight(jsonObject.getString("height"));
            file.setWidth(jsonObject.getString("width"));
            file.setLiveType(jsonObject.getString("liveType"));
            file.setM3u8(jsonObject.getString("m3u8"));
            file.setMp4(jsonObject.getString("mp4"));
            response.setData(file);
            return response;
        }else{
            throw new ServiceException(ExceptionConstant.EXCEPTION_JSON_ERROR," json:"+json);
        }
    }

}
