package com.ruoyi.web.controller.user.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.controller.user.bean.OrderBean;
import com.ruoyi.web.controller.user.bean.ReqOrder;
import com.ruoyi.web.controller.user.domain.Order;
import com.ruoyi.web.controller.user.domain.OrderChannel;
import com.ruoyi.web.controller.user.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description：校翼用户管理 订单用户
 * @Author：kk
 * @Date：2019/10/14 10:58
 */
@Controller
@Slf4j
@RequestMapping("order")
public class OrderController extends BaseController {

    private @Autowired
    OrderService orderService;
    private @Autowired
    Mapper mapper;

    private String prefix = "order/user";

    @GetMapping("/user")
    public String operator(ModelMap mmap) {

        final List<OrderChannel> orderChannelList = orderService.listChannel();
        mmap.put("channel", orderChannelList);
        return prefix + "/order";
    }

    /**
     * 订单用户
     *
     * @param reqOrder
     * @return
     */
    @PostMapping("/user/list")
    @ResponseBody
    public TableDataInfo list(ReqOrder reqOrder) {

        final OrderBean orderBean = mapper.map(reqOrder, OrderBean.class);
        startPage();
        final List<Order> orders = orderService.listOrder(orderBean);
        return getDataTable(orders);
    }

    /**
     * 导出订单用户
     *
     * @param reqOrder
     * @return
     */
    @Log(title = "导出订单用户", businessType = BusinessType.EXPORT)
    @PostMapping("/user/export")
    @ResponseBody
    public AjaxResult export(ReqOrder reqOrder) {

        final OrderBean orderBean = mapper.map(reqOrder, OrderBean.class);
        orderBean.setOrderBy("");
        logger.info("导出订单用户:{}", orderBean);
        final List<Order> orders = orderService.listOrder(orderBean);
        final ExcelUtil<Order> util = new ExcelUtil<>(Order.class);
        return util.exportExcel(orders, "订单用户");
    }

    /**
     * 订单渠道
     *
     * @return
     */
    @PostMapping("/channel/list")
    public List<OrderChannel> list() {
        final List<OrderChannel> orderChannelList = orderService.listChannel();
        return orderChannelList;
    }
}
