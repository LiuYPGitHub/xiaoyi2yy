package com.ruoyi.web.controller.user.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.controller.common.enums.CooperationStatus;
import com.ruoyi.web.controller.common.enums.SexStatus;
import com.ruoyi.web.controller.user.bean.CooperationBean;
import com.ruoyi.web.controller.user.service.CooperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description：合作 需求列表
 * @Author：kk
 * @Date：2019/9/25 17:57
 */
@Controller
@Slf4j
@RequestMapping("need")
public class CooperationController extends BaseController {

    private String prefix = "need/cooperation";

    private @Autowired
    CooperationService cooperationService;

    /**
     * 需求页面
     *
     * @return
     */
    @GetMapping("/cooperation")
    public String cooperation() {
        return prefix + "/cooperation";
    }

    /**
     * 需求列表
     *
     * @param cooperationBean
     * @return
     */
    @PostMapping("/cooperation/list")
    @ResponseBody
    public TableDataInfo list(CooperationBean cooperationBean) {
        startPage();
        final List<CooperationBean> cooperationBeanList = cooperationService.getCooperationBeanList(cooperationBean);
        cooperationBeanList.stream().map(e -> {
                    e.setTypeName(CooperationStatus.getInfo(e.getType()));
                    e.setSex(SexStatus.getInfo(e.getSex()));
                    return e;
                }
        ).collect(Collectors.toList());
        return getDataTable(cooperationBeanList);
    }

    /**
     * 导出需求
     *
     * @param cooperationBean
     * @return
     */
    @Log(title = "需求", businessType = BusinessType.EXPORT)
    @PostMapping("/cooperation/export")
    @ResponseBody
    public AjaxResult export(CooperationBean cooperationBean) {
        startPage();
        final List<CooperationBean> cooperationBeanList = cooperationService.getCooperationBeanList(cooperationBean);
        cooperationBeanList.stream().map(e -> {
                    e.setTypeName(CooperationStatus.getInfo(e.getType()));
                    return e;
                }
        ).collect(Collectors.toList());
        final ExcelUtil<CooperationBean> cooperationBeanExcelUtil = new ExcelUtil<>(CooperationBean.class);
        return cooperationBeanExcelUtil.exportExcel(cooperationBeanList, "需求列表");
    }

    /**
     * 详情
     *
     * @param cooperationId
     * @param mmap
     * @return
     */
    @GetMapping("/cooperation/detail/{cooperationId}")
    public String detail(@PathVariable("cooperationId") String cooperationId, ModelMap mmap) {
        mmap.put("cooperation", cooperationService.getCoopDetail(cooperationId));

        return prefix + "/detail";
    }


}
