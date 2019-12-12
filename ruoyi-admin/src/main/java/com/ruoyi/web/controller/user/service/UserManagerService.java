package com.ruoyi.web.controller.user.service;

import com.ruoyi.web.controller.user.bean.ResumeBean;
import com.ruoyi.web.controller.user.bean.UserInfoBean;

import java.util.List;

public interface UserManagerService {

    List<UserInfoBean> getUserInfo(UserInfoBean userInfoBean);

    int updateUserById(String userId);

    /**
     * 校验手机号 唯一
     *
     * @param telNum
     * @return
     */
    Boolean checkLeads(String telNum);

    /**
     * 保存 机构运营人员
     */
    int saveLeads(UserInfoBean userInfoBean);


    UserInfoBean getLeadsDetail(String userId);

    List<ResumeBean> getResumeBean(String userId);
}
