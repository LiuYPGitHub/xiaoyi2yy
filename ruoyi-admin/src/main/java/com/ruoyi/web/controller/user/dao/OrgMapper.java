package com.ruoyi.web.controller.user.dao;

import com.ruoyi.web.controller.user.bean.OrgBean;

import java.util.List;

public interface OrgMapper {

    List<OrgBean> selectOrg(OrgBean orgBean);

    List<OrgBean> selectOrgByIds(String[] ids);

    OrgBean selectOrgById(String userId);

    int selectOrgByTel(String telNum);

    int updateOrg(OrgBean orgBean);

    int updateOrgInfo(OrgBean orgBean);

    int updateOrgById(String userId);

    int insertOrg(OrgBean orgBean);

    int insertOrgInfo(OrgBean orgBean);


}
