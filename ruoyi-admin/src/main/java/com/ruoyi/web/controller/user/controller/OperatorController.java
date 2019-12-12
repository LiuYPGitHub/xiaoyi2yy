package com.ruoyi.web.controller.user.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.controller.user.bean.OperatorBean;
import com.ruoyi.web.controller.user.bean.ReqOperator;
import com.ruoyi.web.controller.user.bean.ResOperator;
import com.ruoyi.web.controller.user.bean.ResOrg;
import com.ruoyi.web.controller.user.service.OperatorService;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description：校翼用户管理 运营人员
 * @Author：kk
 * @Date：2019/10/14 10:58
 */
@Controller
@Slf4j
@RequestMapping("org")
public class OperatorController extends BaseController {

    private @Autowired
    OperatorService operatorService;
    private @Autowired
    Mapper mapper;

    private String prefix = "org/operator";

    /**
     * 机构页面
     *
     * @param orgId
     * @param mmap
     * @return
     */
    @GetMapping("/operator/{orgId}")
    public String operator(@PathVariable("orgId") String orgId, ModelMap mmap) {
        logger.info("机构页面入参:{}", orgId);
        final OperatorBean org = operatorService.getOrg(orgId);
        final ResOrg map = mapper.map(org, ResOrg.class);
        map.setOrgId(org.getUserId());
        map.setOrgName(org.getRealName());
        mmap.put("org", map);
        return prefix + "/operator";
    }

    /**
     * 机构页面
     *
     * @return
     */
    @GetMapping("/operator/all")
    public String allOperator() {

        return prefix + "/operatorAll";
    }

    /**
     * 机构 运营人员
     *
     * @param reqOperator
     * @return
     */
    @PostMapping("/operator/list")
    @ResponseBody
    public TableDataInfo list(ReqOperator reqOperator) {
        final OperatorBean operatorBean = mapper.map(reqOperator, OperatorBean.class);
        operatorBean.setRealName(reqOperator.getOperatorName());
        startPage();
        final List<OperatorBean> operatorBeans = operatorService.listOperator(operatorBean);
        logger.info("运营人员:{}", operatorBeans);
        operatorBeans.stream().map(e -> {
            e.setOrgName(operatorService.getOrg(e.getOrgId()) == null ? "" : operatorService.getOrg(e.getOrgId()).getRealName());
            return e;
        }).collect(Collectors.toList());
        return getDataTable(operatorBeans);
    }

    /**
     * 导出机构
     *
     * @param reqOperator
     * @return
     */
    @Log(title = "导出机构", businessType = BusinessType.EXPORT)
    @PostMapping("/operator/export")
    @ResponseBody
    public AjaxResult export(ReqOperator reqOperator) {
        final OperatorBean operatorBean = mapper.map(reqOperator, OperatorBean.class);
        final List<OperatorBean> operatorBeans = operatorService.listOperator(operatorBean);
        final List<ResOperator> collect = operatorBeans.stream().map(e -> {
            final ResOperator map = mapper.map(e, ResOperator.class);
            map.setOperatorName(e.getRealName());
            map.setOrgName(operatorService.getOrg(e.getOrgId()).getRealName());
            return map;
        }).collect(Collectors.toList());

        final ExcelUtil<ResOperator> util = new ExcelUtil<>(ResOperator.class);
        return util.exportExcel(collect, "机构");
    }

    /**
     * 新增机构 运营人员 页面
     *
     * @param orgId
     * @param mmap
     * @return
     */
    @GetMapping("/operator/addOperator/{orgId}")
    public String addOperator(@PathVariable("orgId") String orgId, ModelMap mmap) {

        final OperatorBean org = operatorService.getOrg(orgId);
        final ResOrg map = mapper.map(org, ResOrg.class);
        map.setOrgId(org.getUserId());
        map.setOrgName(org.getRealName());
        mmap.put("org", map);

        return prefix + "/addOperator";
    }

    /**
     * 新增机构 运营人员
     *
     * @param reqOperator
     * @return
     */
    @Log(title = "新增运营人员", businessType = BusinessType.INSERT)
    @PostMapping("/operator/addOperator")
    @ResponseBody
    public AjaxResult addOperator(ReqOperator reqOperator) {
        logger.info("新增运营人员入参:{}" + reqOperator);

        if (!operatorService.checkOperator(reqOperator.getTelNum())) {
            return error("账号已存在！");
        }
        final OperatorBean operatorBean = mapper.map(reqOperator, OperatorBean.class);
        operatorBean.setRealName(reqOperator.getOperatorName());
        operatorBean.setRole(reqOperator.getStatus());
        return toAjax(operatorService.saveOperator(operatorBean));
    }


    /**
     * 编辑机构 页面
     *
     * @param operatorId
     * @param mmap
     * @return
     */
    @GetMapping("/operator/editOperator/{operatorId}")
    public String editOrg(@PathVariable("operatorId") String operatorId, ModelMap mmap) {

        final OperatorBean operatorBean = operatorService.getOrg(operatorId);
        OperatorBean org = operatorService.getOrg(operatorBean.getOrgId());
        final ResOrg map = mapper.map(org, ResOrg.class);
        map.setOrgName(org.getRealName());
        mmap.put("org", map);
        mmap.put("operator", operatorBean);
        return prefix + "/edit";
    }

    /**
     * 编辑 机构运营人员
     *
     * @param reqOperator
     * @return
     */
    @Log(title = "编辑机构运营人员", businessType = BusinessType.UPDATE)
    @PostMapping("/operator/editOperator")
    @ResponseBody
    public AjaxResult editOrg(ReqOperator reqOperator) {
        final OperatorBean operatorBean = mapper.map(reqOperator, OperatorBean.class);
        operatorBean.setRealName(reqOperator.getOperatorName());
        operatorBean.setRole(reqOperator.getStatus());
        logger.info("编辑机构运营人员入参:{}", operatorBean);
        return toAjax(operatorService.updateOperator(operatorBean));
    }

    /**
     * 机构 运营人员状态修改
     *
     * @param reqOperator
     * @return
     */
    @Log(title = "机构状态修改", businessType = BusinessType.UPDATE)
    @PostMapping("/operator/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(ReqOperator reqOperator) {
        logger.info("机构状态修改:{}", reqOperator);
        final OperatorBean operatorBean = mapper.map(reqOperator, OperatorBean.class);
        operatorBean.setUserId(reqOperator.getOperatorId());
        operatorBean.setUserState(reqOperator.getStatus());
        return toAjax(operatorService.changeStatus(operatorBean));
    }

    /**
     * 机构 运营人员管理权限
     *
     * @param reqOperator
     * @return
     */
    @Log(title = "机构状态修改", businessType = BusinessType.UPDATE)
    @PostMapping("/operator/changeAdminStatus")
    @ResponseBody
    public AjaxResult changeAdminStatus(ReqOperator reqOperator) {
        logger.info("机构状态修改:{}", reqOperator);
        final OperatorBean operatorBean = mapper.map(reqOperator, OperatorBean.class);
        operatorBean.setUserId(reqOperator.getOperatorId());
        operatorBean.setRole(reqOperator.getStatus());
        return toAjax(operatorService.changeStatus(operatorBean));
    }

    /**
     * 删除 机构运营人员
     *
     * @param
     * @return
     */
    @Log(title = "删除机构运营人员", businessType = BusinessType.UPDATE)
    @PostMapping("/operator/remove")
    @ResponseBody
    public AjaxResult removeOrg(String ids) {

        return toAjax(operatorService.deleteOperator(ids));
    }

}
