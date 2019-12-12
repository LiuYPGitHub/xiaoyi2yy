package com.ruoyi.web.controller.user.service.impl;

import com.ruoyi.web.controller.user.bean.OrderBean;
import com.ruoyi.web.controller.user.dao.ChannelMapper;
import com.ruoyi.web.controller.user.dao.OrderMapper;
import com.ruoyi.web.controller.user.domain.Order;
import com.ruoyi.web.controller.user.domain.OrderChannel;
import com.ruoyi.web.controller.user.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description：用户订单
 * @Author：kk
 * @Date：2019/10/28 13:59
 */
@Service
public class OrderServiceImpl implements OrderService {

    private @Autowired
    OrderMapper orderMapper;
    private @Autowired
    ChannelMapper channelMapper;

    @Override
    public List<Order> listOrder(OrderBean orderBean) {
        final List<Order> orders = orderMapper.selectOrder(orderBean);
        return orders;
    }

    @Override
    public List<OrderChannel> listChannel() {
        return channelMapper.selectOrderChannel();
    }
}
