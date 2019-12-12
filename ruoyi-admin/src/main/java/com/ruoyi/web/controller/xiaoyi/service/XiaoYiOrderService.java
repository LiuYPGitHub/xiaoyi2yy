package com.ruoyi.web.controller.xiaoyi.service;

import com.ruoyi.web.controller.xiaoyi.bean.XiaoYiOrderBean;
import com.ruoyi.web.controller.xiaoyi.domain.XiaoYiOrder;

import java.util.List;

/**
 * @author CJ
 * @date 2019/10/15
 */
public interface XiaoYiOrderService {

    XiaoYiOrderBean getOrder();

    Integer sumOrders();

    int saveOrder(XiaoYiOrder order);

    List<XiaoYiOrder> queryOrderByNum(String orderNum);

    int updateOrder(XiaoYiOrder order);

}
