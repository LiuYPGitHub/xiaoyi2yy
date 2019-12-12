package com.ruoyi.web.controller.blog.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.web.controller.blog.domain.Case;
import com.ruoyi.web.controller.blog.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 案例
 */
@Controller
@RequestMapping("/blog/case")
public class CaseController extends BaseController {
    private String prefix = "blog/case";

    @Autowired
    private CaseService caseService;

//    @RequiresPermissions("blog:case:view")
    @GetMapping()
    public String operlog() {
        return prefix + "/case";
    }

//    @RequiresPermissions("blog:case:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Case casee) {
        startPage();
        List<Case> list = caseService.selectCaseList(casee);
        return getDataTable(list);
    }

    @GetMapping("/detailed/{evtId}")
//    @RequiresPermissions("blog:case:detailed")
    public String detail(@PathVariable("evtId") String evtId, ModelMap mmap) {
        mmap.put("Case", caseService.selectDetailedById(evtId));
        return prefix + "/detailed";
    }
}
