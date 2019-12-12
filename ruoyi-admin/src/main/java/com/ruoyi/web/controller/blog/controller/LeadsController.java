package com.ruoyi.web.controller.blog.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.controller.blog.domain.Leads;
import com.ruoyi.web.controller.blog.service.LeadsService;
import lombok.extern.log4j.Log4j2;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 案例
 */
@Log4j2
@Controller
@RequestMapping("/blog/leads")
public class LeadsController extends BaseController
{
    private String prefix = "blog/leads";

    @Autowired
    private LeadsService leadsService;

    @Autowired
    private Mapper mapper;

    @GetMapping()
    public String operlog()
    {
        return prefix + "/leads";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Leads leads) {
        startPage();
        List<Leads> list = leadsService.selectLeadsList(leads);
        return getDataTable(list);
    }

    @Log(title = "LEANDS 库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Leads leads) {
        final List<Leads> leadsList = leadsService.selectLeadsList(leads);
        final ExcelUtil<Leads> util = new ExcelUtil<>(Leads.class);
        return util.exportExcel(leadsList, "操作日志");
    }

    @GetMapping("/detailed/{leId}")
    public String detail(@PathVariable("leId")String leId, ModelMap mmap) {
        mmap.put("Leads", leadsService.selectDetailedById(leId));
        return prefix + "/detailed";
    }
}
//        Leads le = mapper.map(leadsType, Leads.class);
//        le.setLeIntenType(JSON.toJSONString(leads.getLeIntenType()));
