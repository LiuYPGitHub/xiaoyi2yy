package com.ruoyi.web.controller.user.service;

import com.ruoyi.web.controller.user.bean.OrderBean;
import com.ruoyi.web.controller.user.domain.Order;
import com.ruoyi.web.controller.user.domain.OrderChannel;

import java.util.List;

public interface OrderService {

    List<Order> listOrder(OrderBean orderBean);

    List<OrderChannel> listChannel();
}
