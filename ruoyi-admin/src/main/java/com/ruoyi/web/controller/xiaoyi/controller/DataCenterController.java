package com.ruoyi.web.controller.xiaoyi.controller;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.web.controller.common.annotation.ShowLogger;
import com.ruoyi.web.controller.xiaoyi.bean.XiaoYiOrderBean;
import com.ruoyi.web.controller.xiaoyi.service.XiaoYiOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 销售报表
 */
@Controller
@Api(tags = {"校翼数据中心"})
//@DataSource(value = DataSourceType.MASTER)
public class DataCenterController extends BaseController {

    @Autowired
    private XiaoYiOrderService xiaoYiOrderService;

    @ShowLogger(info = "统计运营端销售报表信息")
    @ApiOperation(value = "统计运营端销售报表信息",notes = "统计运营端销售报表信息")
    @RequestMapping(path = "/xiaoyi/data/totalMoney",method = RequestMethod.GET)
    public String totalMoney(Model model) {
        XiaoYiOrderBean order = xiaoYiOrderService.getOrder();
        model.addAttribute("order",order);
        return "xiaoyi/data/index";
    }

}
