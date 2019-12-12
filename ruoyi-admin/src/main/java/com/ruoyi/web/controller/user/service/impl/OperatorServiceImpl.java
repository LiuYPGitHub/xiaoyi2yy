package com.ruoyi.web.controller.user.service.impl;

import com.ruoyi.web.controller.common.util.MD5Util;
import com.ruoyi.web.controller.user.bean.OperatorBean;
import com.ruoyi.web.controller.user.dao.OperatorMapper;
import com.ruoyi.web.controller.user.service.OperatorService;
import com.ruoyi.web.controller.user.utils.SnowflakeIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description：机构运营人员管理
 * @Author：kk
 * @Date：2019/10/21 09:58
 */
@Service
public class OperatorServiceImpl implements OperatorService {

    private @Autowired
    SnowflakeIdGenerator snowflakeIdGenerator;
    private @Autowired
    OperatorMapper operatorMapper;

    @Override
    public List<OperatorBean> listOperator(OperatorBean operatorBean) {
        final List<OperatorBean> operatorBeans = operatorMapper.selectOperator(operatorBean);
        return operatorBeans;
    }

    @Override
    public OperatorBean getOrg(String orgId) {
        final OperatorBean operatorBean = operatorMapper.selectOrgById(orgId);

        return operatorBean;
    }

    @Override
    public OperatorBean getOperator(String operatorId) {
        final OperatorBean operatorBean = operatorMapper.selectOrgById(operatorId);

        return operatorBean;
    }

    @Override
    public Boolean checkOperator(String telNum) {
        final int count = operatorMapper.selectOperatorByTel(telNum);
        if (count > 0) {
            return false;
        }
        return true;
    }

    @Override
    public int saveOperator(OperatorBean operatorBean) {

        operatorBean.setUserId(snowflakeIdGenerator.nextId() + "");
        operatorBean.setTelNum(operatorBean.getTelNum());
        operatorBean.setPassword(operatorBean.getPassword());
        operatorBean.setUserState("1");
        operatorBean.setPlatform("2");
        operatorBean.setUserType("3");
        operatorBean.setRole(operatorBean.getRole());
        operatorBean.setPassword(MD5Util.encrypt(operatorBean.getPassword()));

        final int row = operatorMapper.insertOperator(operatorBean);

        final OperatorBean operator = new OperatorBean();
        operator.setUserId(operatorBean.getUserId());
        operator.setUserInfoId(snowflakeIdGenerator.nextId() + "");
        operator.setRealName(operatorBean.getRealName());
        operator.setOrgId(operatorBean.getOrgId());
        operatorMapper.insertOperatorInfo(operator);
        return row;
    }


    @Override
    public int updateOperator(OperatorBean operatorBean) {

        operatorBean.setPassword(MD5Util.encrypt(operatorBean.getPassword()));
        final int row = operatorMapper.updateOperator(operatorBean);

        operatorMapper.updateOperatorInfo(operatorBean);
        return row;
    }

    @Override
    public int changeStatus(OperatorBean operatorBean) {
        return operatorMapper.updateOperator(operatorBean);
    }

    @Override
    public int deleteOperator(String operatorId) {
        return operatorMapper.updateOperatorById(operatorId);
    }

    @Override
    public int operatorCount() {
        return 0;
    }
}
