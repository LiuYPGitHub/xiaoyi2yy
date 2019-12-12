package com.ruoyi.web.controller.user.dao;

import com.ruoyi.web.controller.user.bean.ResumeBean;
import com.ruoyi.web.controller.user.bean.UserInfoBean;

import java.util.List;

/**
 * @Description：用户
 * @Author：kk
 * @Date：2019/9/9 10:02
 */
public interface UserManagerMapper {

    /**
     * 通过手机号码查询用户
     *
     * @param userInfoBean
     * @return 用户对象信息
     */
    List<UserInfoBean> selectUserInfo(UserInfoBean userInfoBean);

    int selectLeadsByTel(String telNum);

    int insertLeads(UserInfoBean userInfoBean);

    int insertLeadsInfo(UserInfoBean userInfoBean);

    /**
     * 查看详情
     *
     * @param userId
     * @return
     */
    UserInfoBean selectUserInfoDetail(String userId);

    /**
     * 获取简历
     *
     * @param userId
     * @return
     */
    List<ResumeBean> selectResume(String userId);

    /**
     * 删除用户
     *
     * @param userId 需要删除的数据ID
     * @return 结果
     */
    public int updateUserById(String userId);

    /**
     * 删除用户info
     *
     * @param userId 需要删除的数据ID
     * @return 结果
     */
    public int updateUserInfoById(String userId);
}
