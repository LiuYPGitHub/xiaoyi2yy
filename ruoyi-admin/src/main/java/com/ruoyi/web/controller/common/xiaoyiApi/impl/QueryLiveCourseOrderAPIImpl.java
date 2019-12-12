package com.ruoyi.web.controller.common.xiaoyiApi.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.web.controller.common.xiaoyiApi.QueryLiveCourseOrderAPI;
import com.ruoyi.web.controller.common.xiaoyiApi.QueryLiveCourseOrderRequest;
import com.ruoyi.web.controller.common.xiaoyiApi.QueryLiveCourseOrderResponse;
import com.ruoyi.web.controller.common.xiaoyiApi.bean.APICourseOrder;
import com.ruoyi.web.controller.common.constant.APIConstant;
import com.ruoyi.web.controller.common.constant.ExceptionConstant;
import com.ruoyi.web.controller.common.exception.ServiceException;
import com.ruoyi.web.controller.common.util.HttpClientUtil;
import com.ruoyi.web.controller.common.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.math.BigDecimal;
import java.util.*;

/**
 * 添翼申学-查询直播课程订单接口实现
 * @author CJ
 * @date 2019/10/14
 */
@Slf4j
@Component
public class QueryLiveCourseOrderAPIImpl implements QueryLiveCourseOrderAPI {
    @Override
    public QueryLiveCourseOrderResponse process(QueryLiveCourseOrderRequest request) {
        checkParms(request);
        String functionCode= APIConstant.API_GETCLASSORDERCODE;
        String classId=request.getClassId();
        String platform=APIConstant.API_PLATFORMTYPE;
        String timestamp=DateFormatUtils.format(Calendar.getInstance().getTime(), "yyyyMMddHHmmss");
        Map<String,String> map=new HashMap<>();
        map.put(APIConstant.API_FUNNTIONCODE,functionCode);
        map.put(APIConstant.API_CLASSID,classId);
        map.put(APIConstant.API_PLATFORM,platform);
        map.put(APIConstant.API_TIMESTAMP,timestamp);
        map.put(APIConstant.API_KEY,MD5Util.Ksort(map));
        String post = HttpClientUtil.doPost(APIConstant.API_GETCLASSORDERURL, map);
        log.info("查询直播课程订单结果:"+post);
        return new QueryLiveCourseOrderResponse(processJson(post));
    }

    @Override
    public void checkParms(QueryLiveCourseOrderRequest request) {
        if(StringUtils.isEmpty(request)){
            throw new ServiceException(ExceptionConstant.EXCEPTION_PARMSISNULL,"参数为空");
        }else if(StringUtils.isEmpty(request.getClassId())){
            throw new ServiceException(ExceptionConstant.EXCEPTION_PARMSISNULL,"课程编号参数为空");
        }
    }

    @Override
    public List<APICourseOrder> processJson(String json) {
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
        if("1".equals(returnCode.toString())) {
            JSONArray returnData = jsonObject.parseArray(jsonObject.getString("returnData"));
            List<APICourseOrder> orders=new ArrayList<>();
            for (Object returnDatum : returnData) {
                JSONObject object = JSONObject.parseObject(returnDatum.toString());
                APICourseOrder order=new APICourseOrder();
                if(StringUtils.isEmpty(object.getString("siteName"))){
                    order.setSiteName("未知机构");
                }else{
                    order.setSiteName(object.getString("siteName"));
                }
                if(StringUtils.isEmpty(object.getString("orderNum"))){
                    order.setSiteName("未知订单");
                }else{
                    order.setOrderNum(object.getString("orderNum"));
                }
                if(StringUtils.isEmpty(object.getString("payStatus"))){
                    order.setSiteName("未知订单状态");
                }else{
                    order.setPayStatus(object.getString("payStatus"));
                }
                if(StringUtils.isEmpty(object.getString("payType"))){
                    order.setPayType("未知类型");
                }else{
                    order.setPayType(object.getString("payType"));
                }
                order.setClassCost(object.getInteger("classCost"));
                order.setTotalCost(object.getInteger("totalCost"));
                order.setUserChannel(object.getString("userChannel"));
                order.setUserMobile(object.getString("userMobile"));
                order.setUserName(object.getString("userName"));
                order.setOrderFinishDate(object.getDate("orderFinishDate"));
                if(StringUtils.isEmpty(object.getString("refundName"))){
                    order.setRefundName("已付款");
                }else{
                    order.setRefundName("已退款");
                    //order.setRefundName(object.getString("refundName"));
                }
                order.setRefundDate(object.getDate("refundDate"));
                orders.add(order);
            }
            return orders;
        }else{
            throw new ServiceException(jsonObject.get("returnCode").toString(),jsonObject.get("returnMessage").toString());
        }
    }

}
