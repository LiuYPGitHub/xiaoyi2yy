package com.ruoyi.web.controller.xiaoyi.service.impl;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.web.controller.xiaoyi.bean.OrderWhereBean;
import com.ruoyi.web.controller.xiaoyi.dao.LiveOrderMapper;
import com.ruoyi.web.controller.xiaoyi.bean.XiaoYiOrderBean;
import com.ruoyi.web.controller.xiaoyi.domain.XiaoYiOrder;
import com.ruoyi.web.controller.xiaoyi.service.XiaoYiOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author CJ
 * @date 2019/10/15
 */
@Transactional
@Service
//@DataSource(value = DataSourceType.MASTER)
public class XiaoYiOrderServiceImpl implements XiaoYiOrderService {

    @Autowired
    private LiveOrderMapper liveOrderMapper;

    @Override
    public XiaoYiOrderBean getOrder() {
        XiaoYiOrderBean xiaoYiOrderBean =new XiaoYiOrderBean();
        OrderWhereBean thisMonth = new OrderWhereBean();
        // 全平台
        thisMonth.setMonth("this");
        xiaoYiOrderBean.setThisMonthMoney_live(liveOrderMapper.liveBroadcastAllMonth(thisMonth));
        thisMonth.setMonth("last");
        xiaoYiOrderBean.setLastMonthMoney_live(liveOrderMapper.liveBroadcastAllMonth(thisMonth));
        thisMonth.setMonth("this");
        xiaoYiOrderBean.setThisMonthMoney_recording(liveOrderMapper.fullMonthRecording(thisMonth));
        thisMonth.setMonth("last");
        xiaoYiOrderBean.setLastMonthMoney_recording(liveOrderMapper.fullMonthRecording(thisMonth));
        // 机构自营
        thisMonth.setMonth("this");
        xiaoYiOrderBean.setThisAllMonthRecording_live(liveOrderMapper.jiThisAllMonthRecording(thisMonth));
        thisMonth.setMonth("last");
        xiaoYiOrderBean.setLastAllMonthRecording_live(liveOrderMapper.jiThisAllMonthRecording(thisMonth));
        thisMonth.setMonth("this");
        xiaoYiOrderBean.setThisAllMonthRecording_recording(liveOrderMapper.jiLateAllMonthRecording(thisMonth));
        thisMonth.setMonth("last");
        xiaoYiOrderBean.setLastAllMonthRecording_recording(liveOrderMapper.jiLateAllMonthRecording(thisMonth));
        // 添翼申学
        thisMonth.setMonth("this");
        xiaoYiOrderBean.setThisAllTianYiMonthRecording_live(liveOrderMapper.tianThisAllMonthRecording(thisMonth));
        thisMonth.setMonth("last");
        xiaoYiOrderBean.setLastAllTianYiMonthRecording_live(liveOrderMapper.tianThisAllMonthRecording(thisMonth));
        thisMonth.setMonth("this");
        xiaoYiOrderBean.setThisAllTianYiMonthRecording_recording(liveOrderMapper.tianLateAllMonthRecording(thisMonth));
        thisMonth.setMonth("last");
        xiaoYiOrderBean.setLastAllTianYiMonthRecording_recording(liveOrderMapper.tianLateAllMonthRecording(thisMonth));


        //全平台本月销售金额
//        OrderWhereBean thisMonth = new OrderWhereBean();
//        thisMonth.setMonth("this");
//        xiaoYiOrderBean.setThisMonthMoney_all(liveOrderMapper.selectOrderByName(thisMonth));
//        thisMonth.setFlag("is");
//        xiaoYiOrderBean.setThisMonthMoney_tianyi(liveOrderMapper.selectOrderByName(thisMonth));
//        thisMonth.setFlag("no");
//        xiaoYiOrderBean.setThisMonthMoney_jigou(liveOrderMapper.selectOrderByName(thisMonth));
//        thisMonth.setMonth("last");
//        thisMonth.setFlag("");
//        xiaoYiOrderBean.setLastMonthMoney_all(liveOrderMapper.selectOrderByName(thisMonth));
//        thisMonth.setFlag("is");
//        xiaoYiOrderBean.setLastMonthMoney_tianyi(liveOrderMapper.selectOrderByName(thisMonth));
//        thisMonth.setFlag("no");
//        xiaoYiOrderBean.setLastMonthMoney_jigou(liveOrderMapper.selectOrderByName(thisMonth));
        return xiaoYiOrderBean;
    }

    @Override
    public Integer sumOrders() {
        Integer total = 0;
        List<Integer> orders = liveOrderMapper.selectOrders();
        for (Integer order : orders) {
            total+=order;
        }
        return total;
    }

    @Override
    public int saveOrder(XiaoYiOrder order) {
        return liveOrderMapper.insertOrder(order);
    }

    @Override
    public List<XiaoYiOrder> queryOrderByNum(String orderNum) {
        return liveOrderMapper.selectByOrder(orderNum);
    }

    @Override
    public int updateOrder(XiaoYiOrder order) {
        return liveOrderMapper.updateOrder(order);
    }
}
