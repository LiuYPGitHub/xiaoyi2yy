package com.ruoyi.web.controller.common.videoApi.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.web.controller.common.constant.APIConstant;
import com.ruoyi.web.controller.common.util.HttpClientUtil;
import com.ruoyi.web.controller.common.util.SHA1Util;
import com.ruoyi.web.controller.common.videoApi.AddVideoCataAPI;
import com.ruoyi.web.controller.common.videoApi.AddVideoCataRequest;
import com.ruoyi.web.controller.common.videoApi.AddVideoCataResopnse;
import com.ruoyi.web.controller.common.videoApi.bean.APICata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 新建保利威视频分类
 * @author CJ
 * @date 2019/12/3
 */
@Component
@Slf4j
public class AddVideoCataAPIImpl implements AddVideoCataAPI {

    @Override
    public AddVideoCataResopnse process(AddVideoCataRequest request) throws NoSuchAlgorithmException {
        Map<String,String> map=new HashMap<>();
        String cataName=request.getCataName();
        //视频分类上级目录 默认:正承教育 (1575345411954)
        String parentId=request.getParentId();
        if(StringUtils.isEmpty(parentId)){
            parentId = "1575345411954";
        }
        String ptime = String.valueOf(Calendar.getInstance().getTimeInMillis());
        map.put("cataname",cataName);
        map.put("parentid",parentId);
        map.put("ptime",ptime);
        String data ="cataname="+cataName+"&parentid="+parentId+"&ptime="+ptime+ APIConstant.API_SECRET_KEY;
        String sign = SHA1Util.sha1(data).toUpperCase();
        map.put("sign",sign);
        String url="http://api.polyv.net/v2/video/"+APIConstant.API_USER_ID+"/addCata";
        String json = HttpClientUtil.doPost(url, map);
        log.info("新建保利威视频分类结果:" + json);
        return processJson(json);
    }

    @Override
    public AddVideoCataResopnse processJson(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String code = jsonObject.getString("code");
        String status = jsonObject.getString("status");
        String message = jsonObject.getString("message");
        if("200".equals(code)){
            APICata cata = JSONObject.parseObject(jsonObject.getString("data"), APICata.class);
            AddVideoCataResopnse resopnse=new AddVideoCataResopnse();
            resopnse.setCode(code);
            resopnse.setStatus(status);
            resopnse.setMessage(message);
            resopnse.setCata(cata);
            return resopnse;
        }else{
            log.error("新建保利威视频分类结果:"+json);
            return null;
        }
    }

}
