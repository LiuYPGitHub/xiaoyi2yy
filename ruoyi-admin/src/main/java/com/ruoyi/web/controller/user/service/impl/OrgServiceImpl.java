package com.ruoyi.web.controller.user.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.web.controller.user.bean.OrgBean;
import com.ruoyi.web.controller.user.dao.OperatorMapper;
import com.ruoyi.web.controller.user.dao.OrgMapper;
import com.ruoyi.web.controller.user.service.OrgService;
import com.ruoyi.web.controller.user.utils.SnowflakeIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description：leads
 * @Author：kk
 * @Date：2019/10/14 09:45
 */
@Service
@Slf4j
public class OrgServiceImpl implements OrgService {

    private @Autowired
    SnowflakeIdGenerator snowflakeIdGenerator;
    private @Autowired
    OrgMapper orgMapper;
    private @Autowired
    OperatorMapper operatorMapper;


    @Override
    public List<OrgBean> listOrg(OrgBean orgBean) {

        final List<OrgBean> orgBeans = orgMapper.selectOrg(orgBean);
        return orgBeans;
    }

    @Override
    public List<OrgBean> listOrgByIds(String ids) {
        final List<OrgBean> orgBeans = orgMapper.selectOrgByIds(Convert.toStrArray(ids));
        return orgBeans;
    }

    @Override
    public OrgBean getOrg(String orgId) {
        final OrgBean orgBean = orgMapper.selectOrgById(orgId);
        return orgBean;
    }

    @Override
    public Boolean checkOrg(String telNum) {
        final int count = orgMapper.selectOrgByTel(telNum);
        if (count > 0) {
            return false;
        }
        return true;
    }

    @Override
    public int saveOrg(OrgBean orgBean) {
        log.info("保存机构：{}", orgBean);
        final OrgBean leadsUser = new OrgBean();
        leadsUser.setUserId(snowflakeIdGenerator.nextId() + "");
        leadsUser.setUserType("3");
        leadsUser.setUserState("1");
        leadsUser.setPlatform("2");
        orgMapper.insertOrg(leadsUser);

        final OrgBean leadsUserInfo = new OrgBean();
        leadsUserInfo.setRealName(orgBean.getRealName());
        leadsUserInfo.setPhone(orgBean.getPhone());
        leadsUserInfo.setUserId(leadsUser.getUserId());
        leadsUserInfo.setUserInfoId(snowflakeIdGenerator.nextId() + "");
        leadsUserInfo.setCampus(orgBean.getCampus());
        leadsUserInfo.setContacts(orgBean.getContacts());
        leadsUserInfo.setPersonalInfo(orgBean.getPersonalInfo());
        leadsUserInfo.setAddress(orgBean.getAddress());
        final int row = orgMapper.insertOrgInfo(leadsUserInfo);

        return row;
    }

    @Override
    public int saveKT(OrgBean orgBean) {

        final OrgBean leadsUser = new OrgBean();
        leadsUser.setUserId(snowflakeIdGenerator.nextId() + "");
        leadsUser.setTelNum(orgBean.getTelNum());
        leadsUser.setUserType(orgBean.getUserType());
        leadsUser.setUserState("1");
        orgMapper.insertOrg(leadsUser);

        final OrgBean leadsUserInfo = new OrgBean();
        leadsUserInfo.setUserId(leadsUser.getUserId());
        leadsUserInfo.setUserInfoId(snowflakeIdGenerator.nextId() + "");
        leadsUserInfo.setRealName(orgBean.getRealName());
        leadsUserInfo.setNickName(orgBean.getNickName());
        final int row = orgMapper.insertOrgInfo(leadsUserInfo);

        return row;
    }

    @Override
    public Integer countOperator(String orgId) {
        final Integer countOperator = operatorMapper.countOperator(orgId);
        return countOperator;
    }

    @Override
    public int updateOrg(OrgBean entity) {
        orgMapper.updateOrg(entity);
        orgMapper.updateOrgInfo(entity);
        return orgMapper.updateOrgInfo(entity);
    }

    @Override
    public int changeStatus(OrgBean orgBean) {
        return orgMapper.updateOrg(orgBean);
    }

    @Override
    public int deleteOrg(String orgId) {

        return orgMapper.updateOrgById(orgId);
    }

    @Override
    public int operatorCount() {
        return 0;
    }
}
