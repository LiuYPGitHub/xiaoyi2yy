package com.ruoyi.web.controller.user.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.web.controller.common.enums.UserAccStatus;
import com.ruoyi.web.controller.common.enums.UserTypeStatus;
import com.ruoyi.web.controller.common.util.MD5Util;
import com.ruoyi.web.controller.user.bean.AreaCodeBean;
import com.ruoyi.web.controller.user.bean.ResumeBean;
import com.ruoyi.web.controller.user.bean.UserInfoBean;
import com.ruoyi.web.controller.user.dao.AreaCodeMapper;
import com.ruoyi.web.controller.user.dao.UserManagerMapper;
import com.ruoyi.web.controller.user.service.UserManagerService;
import com.ruoyi.web.controller.user.utils.SnowflakeIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description：用户
 * @Author：kk
 * @Date：2019/9/9 13:04
 */
@Service
public class UserServiceImpl implements UserManagerService {

    private @Autowired
    UserManagerMapper userManagerMapper;
    private @Autowired
    AreaCodeMapper areaCodeMapper;

    private @Autowired
    SnowflakeIdGenerator snowflakeIdGenerator;

    @Override
    public List<UserInfoBean> getUserInfo(UserInfoBean userInfoBean) {
        final List<UserInfoBean> userInfoBeans = userManagerMapper.selectUserInfo(userInfoBean);
        return userInfoBeans;
    }

    @Override
    public int updateUserById(String userId) {

        final int row = userManagerMapper.updateUserById(userId);
        userManagerMapper.updateUserInfoById(userId);
        return row;
    }

    @Override
    public Boolean checkLeads(String telNum) {
        final int count = userManagerMapper.selectLeadsByTel(telNum);
        if (count > 0) {
            return false;
        }
        return true;
    }

    @Override
    public int saveLeads(UserInfoBean userInfoBean) {

        userInfoBean.setUserId(snowflakeIdGenerator.nextId() + "");
        userInfoBean.setTelNum(userInfoBean.getTelNum());
        if (StringUtils.isNotBlank(userInfoBean.getPassword())) {
            userInfoBean.setPassword(MD5Util.encrypt(userInfoBean.getPassword()));
        }
        userInfoBean.setUserType(userInfoBean.getUserType());
        final int row = userManagerMapper.insertLeads(userInfoBean);

        userInfoBean.setUserId(userInfoBean.getUserId());
        userInfoBean.setUserInfoId(snowflakeIdGenerator.nextId() + "");
        userInfoBean.setRealName(userInfoBean.getRealName());
        userInfoBean.setNickName(userInfoBean.getNickName());
        userManagerMapper.insertLeadsInfo(userInfoBean);
        return row;
    }


    @Override
    public UserInfoBean getLeadsDetail(String userId) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final UserInfoBean userInfoBean = userManagerMapper.selectUserInfoDetail(userId);
        userInfoBean.setUserStateName(UserAccStatus.getInfo(userInfoBean.getUserState()));
        userInfoBean.setUserTypeName(UserTypeStatus.getInfo(userInfoBean.getUserType()));
        if (userInfoBean.getBirthday() != null) {
            userInfoBean.setStrBirthday(simpleDateFormat.format(userInfoBean.getBirthday()));
        }

        if (org.apache.commons.lang3.StringUtils.isNotEmpty(userInfoBean.getCity())) {
            final AreaCodeBean areaCode = areaCodeMapper.selectAreaCode(userInfoBean.getCity());
            userInfoBean.setCity(StringUtils.equals(areaCode.getAreaPCode(), "0") ? "" : areaCodeMapper.selectAreaCode(areaCode.getAreaPCode()).getAreaName() + "-" + areaCode.getAreaName());
        }

        final List<String> strings = com.alibaba.fastjson.JSON.parseArray(userInfoBean.getLabels(), String.class);
        userInfoBean.setLabels(StringUtils.join(strings, ","));
        final List<ResumeBean> resumeBeans = userManagerMapper.selectResume(userId);
        final List<ResumeBean> collect = resumeBeans.stream().map(e -> {
            e.setStrStartDate(simpleDateFormat.format(e.getStartDate()));
            e.setStrEndDate(e.getEndDate() == null ? simpleDateFormat.format(new Date()) : simpleDateFormat.format(e.getEndDate()));
            return e;
        }).collect(Collectors.toList());
        userInfoBean.setResume(collect);
        return userInfoBean;
    }

    @Override
    public List<ResumeBean> getResumeBean(String userId) {

        return userManagerMapper.selectResume(userId);
    }
}
