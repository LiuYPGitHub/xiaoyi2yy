package com.ruoyi.web.controller.user.service;

import com.ruoyi.web.controller.user.bean.OrgBean;

import java.util.List;

public interface OrgService {

    /**
     * 机构 列表
     *
     * @param orgBean
     * @return
     */
    List<OrgBean> listOrg(OrgBean orgBean);

    /**
     * 机构 列表
     *
     * @param ids
     * @return
     */
    List<OrgBean> listOrgByIds(String ids);

    /**
     * 详情
     *
     * @param orgId
     * @return
     */
    OrgBean getOrg(String orgId);

    /**
     * 校验手机号 唯一
     *
     * @param telNum
     * @return
     */
    Boolean checkOrg(String telNum);

    /**
     * 保存 机构
     */
    int saveOrg(OrgBean orgBean);

    /**
     * 保存 KOL/老师
     *
     * @param orgBean
     * @return
     */
    int saveKT(OrgBean orgBean);

    /**
     * 统计 运营人员
     *
     * @return
     */
    Integer countOperator(String orgId);

    /**
     * 修改机构
     *
     * @param orgBean
     * @return
     */
    int updateOrg(OrgBean orgBean);

    /**
     * 启禁用
     *
     * @param orgBean
     * @return
     */
    int changeStatus(OrgBean orgBean);

    /**
     * 删除机构
     *
     * @param orgId
     * @return
     */
    int deleteOrg(String orgId);

    int operatorCount();
}
