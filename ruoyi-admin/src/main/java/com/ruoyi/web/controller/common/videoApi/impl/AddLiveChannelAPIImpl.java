package com.ruoyi.web.controller.common.videoApi.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.web.controller.common.constant.APIConstant;
import com.ruoyi.web.controller.common.util.AESUtil;
import com.ruoyi.web.controller.common.util.HttpClientUtil;
import com.ruoyi.web.controller.common.util.MD5HexUtil;
import com.ruoyi.web.controller.common.videoApi.AddLiveChannelAPI;
import com.ruoyi.web.controller.common.videoApi.AddLiveChannelRequest;
import com.ruoyi.web.controller.common.videoApi.AddLiveChannelResopnse;
import com.ruoyi.web.controller.common.videoApi.bean.APILiveChannel;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 添加直播频道接口实现
 * @author CJ
 * @date 2019/12/3
 */
@Component
@Slf4j
public class AddLiveChannelAPIImpl implements AddLiveChannelAPI {

    @Value(value = "xiaoYiService.channelPasswd")
    private String channelPasswd;

    @Override
    public AddLiveChannelResopnse process(AddLiveChannelRequest request) {
        String name = request.getName();
        Map<String,String> map=new HashMap<>();
        String ptime = String.valueOf(Calendar.getInstance().getTimeInMillis());
        map.put("appId", APIConstant.API_APP_ID);
        map.put("timestamp",ptime);
        map.put("userId",APIConstant.API_USER_ID);
        map.put("name",name);
        if(!StringUtils.isEmpty(request.getPwd())){
            //不为空使用用户填写的密码
            map.put("channelPasswd", request.getPwd());
        }else{
            //为空使用默认密码 123456
            map.put("channelPasswd", AESUtil.decryptStart(channelPasswd));
        }
        String ksort = MD5HexUtil.Ksort(map);
        map.put("sign",ksort);
        String url = "http://api.polyv.net/live/v2/channels/";
        String json = HttpClientUtil.doPost(url, map);
        log.info("添加直播频道结果:" + json);
        return processJson(json);
    }

    @Override
    public AddLiveChannelResopnse processJson(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String code = jsonObject.getString("code");
        String status = jsonObject.getString("status");
        String message = jsonObject.getString("message");
        if("200".equals(code)){
            APILiveChannel channel = JSONObject.parseObject(jsonObject.getString("data"), APILiveChannel.class);
            AddLiveChannelResopnse resopnse=new AddLiveChannelResopnse();
            resopnse.setCode(code);
            resopnse.setStatus(status);
            resopnse.setMessage(message);
            resopnse.setChannel(channel);
            return resopnse;
        }else{
            return null;
        }
    }

}
