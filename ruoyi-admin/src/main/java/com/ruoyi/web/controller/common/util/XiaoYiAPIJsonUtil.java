package com.ruoyi.web.controller.common.util;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.web.controller.common.constant.ExceptionConstant;
import com.ruoyi.web.controller.common.exception.ServiceException;
import org.springframework.util.StringUtils;

/**
 * @author CJ
 * @date 2019/10/12
 */
public class XiaoYiAPIJsonUtil {

    public static String getJsonResult(String json,String typeId){
        if(StringUtils.isEmpty(json)){
            throw new ServiceException(ExceptionConstant.EXCEPTION_PARMSISNULL,"返回参数为空");
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        if(StringUtils.isEmpty(jsonObject)){
            throw new ServiceException(ExceptionConstant.EXCEPTION_PARMSISNULL,"返回参数为空");
        }
        Object returnCode = jsonObject.get("returnCode");
        if(StringUtils.isEmpty(returnCode)){
            throw new ServiceException(ExceptionConstant.EXCEPTION_PARMSISNULL,"返回参数状态码为空");
        }
        if("1".equals(returnCode.toString())){
            JSONObject returnData = JSONObject.parseObject(jsonObject.get("returnData").toString());
            return returnData.getString(typeId);
        }else{
            throw new ServiceException(jsonObject.get("returnCode").toString(),jsonObject.get("returnMessage").toString());
        }
    }

}
