package com.ruoyi.web.controller.user.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.web.controller.common.authentication.JWTUtil;
import com.ruoyi.web.controller.user.bean.ReqLeads;
import com.ruoyi.web.controller.user.bean.ReqUserInfo;
import com.ruoyi.web.controller.user.bean.UserInfoBean;
import com.ruoyi.web.controller.user.service.UserManagerService;
import com.ruoyi.web.controller.user.utils.FebsUtil;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 公告 信息操作处理
 *
 * @author ruoyi
 */
@Controller
@Slf4j
@RequestMapping("user/manager")
public class UserManagerController extends BaseController {
    private String prefix = "user/manager";

    private @Autowired
    UserManagerService userManagerService;
    private @Autowired
    Mapper mapper;

    @GetMapping("/kol")
    public String kolManager() {
        return prefix + "/kol";
    }

    @GetMapping("/teacher")
    public String teacherManager() {
        return prefix + "/teacher";
    }

    @GetMapping("/org")
    public String orgManager() {
        return prefix + "/org";
    }

    /**
     * 查询用户列表
     */
    @PostMapping("/list/{userType}")
    @ResponseBody
    public TableDataInfo list(ReqUserInfo reqUserInfo, @PathVariable("userType") String userType) {

        final UserInfoBean userInfoBean = mapper.map(reqUserInfo, UserInfoBean.class);
        userInfoBean.setUserType(userType);
        startPage();
        final List<UserInfoBean> userInfo = userManagerService.getUserInfo(userInfoBean);

        userInfo.stream().map(e -> {
            final List<String> strings = com.alibaba.fastjson.JSON.parseArray(e.getLabels(), String.class);
            e.setLabels(StringUtils.join(strings, ","));
            e.setPassword(StringUtils.isEmpty(e.getPassword()) ? "1" : "2");
            e.setToken(FebsUtil.encryptToken(JWTUtil.sign(e.getTelNum(), "zc")));
            return e;
        }).collect(Collectors.toList());

        return getDataTable(userInfo);
    }

    /**
     * 新增leads 用户
     *
     * @param mmap
     * @return
     */
    @GetMapping("/add/{userType}")
    public String addOperator(@PathVariable("userType") String userType, ModelMap mmap) {
        mmap.put("type", userType);
        return prefix + "/add";
    }

    /**
     * 新增leads 用户
     *
     * @param reqLeads
     * @return
     */
    @Log(title = "新增运营人员", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addOperator(ReqLeads reqLeads) {

        logger.info("新增leads 入参:{}" + reqLeads);
        if (!userManagerService.checkLeads(reqLeads.getTelNum())) {
            return error("账号已存在！");
        }
        final UserInfoBean map = mapper.map(reqLeads, UserInfoBean.class);
        return toAjax(userManagerService.saveLeads(map));
    }

    /**
     * 详情页面
     *
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/detail/{userId}")
    public String detail(@PathVariable("userId") String userId, ModelMap mmap) {
        mmap.put("leads", userManagerService.getLeadsDetail(userId));
        System.out.println("leads:" + userManagerService.getLeadsDetail(userId).getResume());
        return prefix + "/detail";
    }

    /**
     * 删除用户
     *
     * @param ids
     * @return
     */
    @Log(title = "删除用户", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(userManagerService.updateUserById(ids));
    }

}
