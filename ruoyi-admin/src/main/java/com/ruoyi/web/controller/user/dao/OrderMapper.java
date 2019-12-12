package com.ruoyi.web.controller.user.dao;

import com.ruoyi.web.controller.user.bean.OrderBean;
import com.ruoyi.web.controller.user.domain.Order;

import java.util.List;

public interface OrderMapper {

    List<Order> selectOrder(OrderBean orderBean);

}
