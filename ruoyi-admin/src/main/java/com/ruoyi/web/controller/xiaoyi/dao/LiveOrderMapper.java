package com.ruoyi.web.controller.xiaoyi.dao;

import com.ruoyi.web.controller.xiaoyi.bean.OrderWhereBean;
import com.ruoyi.web.controller.xiaoyi.domain.XiaoYiOrder;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author CJ
 * @date 2019/10/15
 */
public interface LiveOrderMapper {

//    BigDecimal selectOrderByName(OrderWhereBean whereBean);

    BigDecimal liveBroadcastAllMonth(OrderWhereBean whereBean);

    BigDecimal fullMonthRecording(OrderWhereBean whereBean);

    BigDecimal jiThisAllMonthRecording(OrderWhereBean whereBean);

    BigDecimal jiLateAllMonthRecording(OrderWhereBean whereBean);

    BigDecimal tianThisAllMonthRecording(OrderWhereBean whereBean);

    BigDecimal tianLateAllMonthRecording(OrderWhereBean whereBean);

    List<Integer> selectOrders();

    int insertOrder(XiaoYiOrder order);

    List<XiaoYiOrder> selectByOrder(@Param("orderNum") String orderNum);

    int updateOrder(XiaoYiOrder order);

}
