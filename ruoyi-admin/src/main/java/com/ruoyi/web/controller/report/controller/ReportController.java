package com.ruoyi.web.controller.report.controller;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.web.controller.report.dto.CourseIndexResponse;
import com.ruoyi.web.controller.report.dto.DateResponse;
import com.ruoyi.web.controller.report.dto.institutions6Response;
import com.ruoyi.web.controller.report.service.ReportService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Report form
 */
@Slf4j
@Controller
//@DataSource(value = DataSourceType.MASTER)
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/clas")
    public String clas(ModelMap mmap,String orgCode) {
        mmap.put("court", reportService.coPending(orgCode));
        mmap.put("online", reportService.online(orgCode));
        mmap.put("notThrough", reportService.notThrough(orgCode));

        mmap.put("sales", reportService.sales(orgCode));
        mmap.put("ownChannelSales", reportService.ownChannelSales(orgCode));
        mmap.put("wings", reportService.wings(orgCode));

        mmap.put("students", reportService.students(orgCode));
        mmap.put("ownChannel", reportService.ownChannel(orgCode));
        mmap.put("wingsOwn", reportService.wingsOwn(orgCode));

        mmap.put("selectSiteName", reportService.selectSiteName(orgCode));
        mmap.put("biaoji",orgCode);
        return "main";
    }

    @GetMapping("/TOP5")
    @ResponseBody
    public AjaxResult TOP5(String orgCode) {
        try {
            CourseIndexResponse response = reportService.CourseName(orgCode);
            return AjaxResult.success(response);
        } catch (Exception e) {
            return AjaxResult.error("热销课程TOP5加载失败!" + e);
        }
    }

    @GetMapping("/class14")
    @ResponseBody
    public AjaxResult class14(String siteName,String orgCode) {
        try {
            DateResponse date = reportService.dateFormat(siteName,orgCode);
            return AjaxResult.success(date);
        } catch (Exception e) {
            return AjaxResult.error("机构14日数据加载失败!" + e);
        }
    }

    @GetMapping("/institutions6")
    @ResponseBody
    public AjaxResult institutions6(String orgCode) {
        try {
            institutions6Response inst = reportService.institutions6(orgCode);
            return AjaxResult.success(inst);
        } catch (Exception e) {
            return AjaxResult.error("近6月销售数据加载失败!" + e);
        }
    }
}