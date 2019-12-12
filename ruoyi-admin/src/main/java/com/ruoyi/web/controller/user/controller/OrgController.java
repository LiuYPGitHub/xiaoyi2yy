package com.ruoyi.web.controller.user.controller;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.controller.common.ResPageInfo;
import com.ruoyi.web.controller.user.bean.OrgBean;
import com.ruoyi.web.controller.user.bean.ReqOrg;
import com.ruoyi.web.controller.user.bean.ResOrg;
import com.ruoyi.web.controller.user.service.OrgService;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description：校翼用户管理
 * @Author：kk
 * @Date：2019/10/14 10:58
 */
@Controller
@Slf4j
@RequestMapping("manager")
public class OrgController extends BaseController {

    private @Autowired
    OrgService orgService;
    private @Autowired
    Mapper mapper;

    private String prefix = "org/manager";

    @GetMapping("/org")
    public String orgList() {
        return prefix + "/org";
    }

    /**
     * 机构
     *
     * @param reqOrg
     * @return
     */
    @PostMapping("/org/list")
    @ResponseBody
    public TableDataInfo list(ReqOrg reqOrg) {
        final OrgBean orgBean = mapper.map(reqOrg, OrgBean.class);
        orgBean.setRealName(reqOrg.getOrgName());
        startPage();
        final List<OrgBean> orgBeans = orgService.listOrg(orgBean);
        final PageInfo<OrgBean> orgBeanPageInfo = new PageInfo<>(orgBeans);
        final List<ResOrg> resOrgs = orgBeans.stream().map(e -> {
            final ResOrg map = mapper.map(e, ResOrg.class);
            map.setOperatorCount(orgService.countOperator(e.getUserId()));
            map.setOrgName(e.getRealName());
            map.setOrgId(e.getUserId());
            map.setUserState(e.getUserState());
            return map;
        }).collect(Collectors.toList());
        final ResPageInfo<ResOrg> resPageInfo = new ResPageInfo<>();
        mapper.map(orgBeanPageInfo, resPageInfo);
        resPageInfo.setContent(resOrgs);
        final TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setCode(0);
        tableDataInfo.setRows(resPageInfo.getContent());
        tableDataInfo.setTotal(resPageInfo.getTotal());
        return tableDataInfo;
    }

    @Log(title = "导出机构", businessType = BusinessType.EXPORT)
    @PostMapping("/org/export")
    @ResponseBody
    public AjaxResult export(String ids) {
        log.info("导出机构:{}", ids);
        final List<OrgBean> orgBeans = orgService.listOrgByIds(ids);
        final List<ResOrg> resOrgs = orgBeans.stream().map(e -> {
            final ResOrg map = mapper.map(e, ResOrg.class);
            map.setOperatorCount(orgService.countOperator(e.getUserId()));
            map.setOrgName(e.getRealName());
            map.setOrgId(e.getUserId());
            map.setUserState(e.getUserState());
            map.setInfo(e.getPersonalInfo());
            return map;
        }).collect(Collectors.toList());
        final ExcelUtil<ResOrg> util = new ExcelUtil<>(ResOrg.class);
        return util.exportExcel(resOrgs, "机构");

    }

    @Log(title = "导出全部机构", businessType = BusinessType.EXPORT)
    @PostMapping("/org/all/export")
    @ResponseBody
    public AjaxResult allExport(ReqOrg reqOrg) {
        log.info("导出机构:{}", reqOrg);
        final OrgBean orgBean = mapper.map(reqOrg, OrgBean.class);
        orgBean.setRealName(reqOrg.getOrgName());
        final List<OrgBean> orgBeans = orgService.listOrg(orgBean);
        final List<ResOrg> resOrgs = orgBeans.stream().map(e -> {
            final ResOrg map = mapper.map(e, ResOrg.class);
            map.setOperatorCount(orgService.countOperator(e.getUserId()));
            map.setOrgName(e.getRealName());
            map.setOrgId(e.getUserId());
            map.setUserState(e.getUserState());
            map.setInfo(e.getPersonalInfo());
            return map;
        }).collect(Collectors.toList());
        final ExcelUtil<ResOrg> util = new ExcelUtil<>(ResOrg.class);
        return util.exportExcel(resOrgs, "机构");
    }

    /**
     * 新增机构 页面
     *
     * @return
     */
    @GetMapping("/org/addOrg")
    public String addOrg() {
        return prefix + "/add";
    }

    /**
     * 新增机构
     *
     * @param reqOrg
     * @return
     */
    @Log(title = "新增机构", businessType = BusinessType.INSERT)
    @PostMapping("/org/addOrg")
    @ResponseBody
    public AjaxResult addOrg(ReqOrg reqOrg) {

        logger.info("新增机构入参:{}", reqOrg);
        final OrgBean orgBean = mapper.map(reqOrg, OrgBean.class);
        orgBean.setRealName(reqOrg.getOrgName());
        orgBean.setPersonalInfo(reqOrg.getInfo());
        return toAjax(orgService.saveOrg(orgBean));
    }

    /**
     * 编辑机构 页面
     *
     * @param orgId
     * @param mmap
     * @return
     */
    @GetMapping("/org/editOrg/{orgId}")
    public String editOrg(@PathVariable("orgId") String orgId, ModelMap mmap) {

        final OrgBean org = orgService.getOrg(orgId);
        logger.info("编辑机构:{}",org);
        final ResOrg map = mapper.map(org, ResOrg.class);
        map.setOrgId(org.getUserId());
        map.setOrgName(org.getRealName());
        map.setInfo(org.getPersonalInfo());
        mmap.put("org", map);

        return prefix + "/edit";
    }

    /**
     * 编辑机构
     *
     * @param reqOrg
     * @return
     */
    @Log(title = "编辑机构", businessType = BusinessType.UPDATE)
    @PostMapping("/org/editOrg")
    @ResponseBody
    public AjaxResult editOrg(ReqOrg reqOrg) {
        final OrgBean orgBean = mapper.map(reqOrg, OrgBean.class);
        orgBean.setUserId(reqOrg.getOrgId());
        orgBean.setRealName(reqOrg.getOrgName());
        orgBean.setPersonalInfo(reqOrg.getInfo());
        return toAjax(orgService.updateOrg(orgBean));
    }

    /**
     * 机构状态修改
     *
     * @param reqOrg
     * @return
     */
    @Log(title = "机构状态修改", businessType = BusinessType.UPDATE)
    @PostMapping("/org/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(ReqOrg reqOrg) {
        final OrgBean orgBean = mapper.map(reqOrg, OrgBean.class);
        orgBean.setUserId(reqOrg.getOrgId());
        return toAjax(orgService.changeStatus(orgBean));
    }

    /**
     * 删除机构
     *
     * @param ids
     * @return
     */
    @Log(title = "删除机构", businessType = BusinessType.UPDATE)
    @PostMapping("/org/remove")
    @ResponseBody
    public AjaxResult removeOrg(String ids) {

        return toAjax(orgService.deleteOrg(ids));
    }

}
