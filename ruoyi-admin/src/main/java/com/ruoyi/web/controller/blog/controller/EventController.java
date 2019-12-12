package com.ruoyi.web.controller.blog.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.web.controller.blog.domain.Case;
import com.ruoyi.web.controller.blog.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 活动
 */
@Controller
@RequestMapping("/blog/event")
public class EventController extends BaseController {

    private String prefix = "blog/event";

    @Autowired
    private EventService eventService;

//    @RequiresPermissions("blog:event:view")
    @GetMapping()
    public String operlog() {
        return prefix + "/event";
    }

//    @RequiresPermissions("blog:event:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Case casee) {
        startPage();
        List<Case> list = eventService.selectCaseList(casee);
        return getDataTable(list);
    }

    @GetMapping("/detailed/{evtId}")
//    @RequiresPermissions("blog:event:detailed")
    public String detail(@PathVariable("evtId") String evtId, ModelMap mmap) {
            mmap.put("Case", eventService.selectDetailedById(evtId));
            return prefix + "/detailed";
     }

}
