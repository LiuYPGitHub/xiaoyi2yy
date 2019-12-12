package com.ruoyi.web.controller.blog.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.web.controller.blog.bean.DynamicBean;
import com.ruoyi.web.controller.blog.bean.ReqDynamic;
import com.ruoyi.web.controller.blog.service.DynamicService;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description：动态管理
 * @Author：kk
 */
@Controller
@Slf4j
@RequestMapping("blog")
public class DynamicController extends BaseController {
    private String prefix = "blog/dynamic";

    private @Autowired
    DynamicService dynamicService;
    private @Autowired
    Mapper mapper;

    @GetMapping("/dynamic")
    public String dynamic() {
        return prefix + "/dynamic";
    }

    /**
     * 动态管理
     */
    //@RequiresPermissions("system:notice:list")
    @PostMapping("/dynamic/list")
    @ResponseBody
    public TableDataInfo list(ReqDynamic reqDynamic) {

        logger.info("动态管理:" + reqDynamic);
        final DynamicBean dynamicBean = mapper.map(reqDynamic, DynamicBean.class);
        startPage();
        final List<DynamicBean> dynamic = dynamicService.getDynamic(dynamicBean);
        final List<DynamicBean> collect = dynamic.stream().map(e -> {
            e.setUserType(StringUtils.equals("1", e.getUserType()) ? "KOL" : "名师");
            return e;
        }).collect(Collectors.toList());

        return getDataTable(collect);
    }

    @GetMapping("/dynamic/detail/{dynamicId}")
    public String detail(@PathVariable("dynamicId") Long dynaId, ModelMap mmap) {
        mmap.put("dynamic", dynamicService.getDynamicDetail(dynaId));
        return prefix + "/detail";
    }


}
