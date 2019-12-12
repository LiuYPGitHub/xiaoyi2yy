package com.ruoyi.web.controller.common.videoApi.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ruoyi.web.controller.common.constant.APIConstant;
import com.ruoyi.web.controller.common.constant.ExceptionConstant;
import com.ruoyi.web.controller.common.exception.ServiceException;
import com.ruoyi.web.controller.common.util.HttpClientUtil;
import com.ruoyi.web.controller.common.util.SHA1Util;
import com.ruoyi.web.controller.common.videoApi.BaseCheck;
import com.ruoyi.web.controller.common.videoApi.QueryVideoClassAPI;
import com.ruoyi.web.controller.common.videoApi.QueryVideoClassRequest;
import com.ruoyi.web.controller.common.videoApi.QueryVideoClassResponse;
import com.ruoyi.web.controller.common.videoApi.bean.APINode;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;

/**
 * @author CJ
 * @date 2019/10/22
 */
@Component
public class QueryVideoClassAPIImpl extends BaseCheck implements QueryVideoClassAPI {

    @Override
    public QueryVideoClassResponse process(QueryVideoClassRequest request) throws NoSuchAlgorithmException {
        String ptime=String.valueOf(Calendar.getInstance().getTimeInMillis());
        String userId= APIConstant.API_USER_ID;
        String data = "ptime="+ptime+"&userid="+ userId +APIConstant.API_SECRET_KEY;
        String sign = SHA1Util.sha1(data).toUpperCase();
        String url="http://api.polyv.net/v2/video/"+userId+"/cataJson?ptime="+ptime+"&sign="+sign+"&cataId=1499328808069";
        String json = HttpClientUtil.doGet(url);
        return processJson(json);
    }

    @Override
    public QueryVideoClassResponse processJson(String json) {
        checkJson(json);
        JSONObject object = JSONObject.parseObject(json);
        Object code = object.get("code");
        QueryVideoClassResponse response=new QueryVideoClassResponse();
        if(!StringUtils.isEmpty(code) && "200".equals(code.toString())){
            response.setCode(object.getString("code"));
            response.setStatus(object.getString("status"));
            response.setMessage(object.getString("message"));
            String dataJson = object.getString("data");
            Type listType = new TypeToken<List<APINode>>() {}.getType();
            List<APINode> apiNodes = new Gson().fromJson(dataJson, listType);
            response.setData(apiNodes);
        }else{
            throw new ServiceException(ExceptionConstant.EXCEPTION_JSON_ERROR," json:"+json);
        }
        return response;
    }

}
