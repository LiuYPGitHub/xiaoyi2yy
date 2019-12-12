package com.ruoyi.web.controller.user.dao;

import com.ruoyi.web.controller.user.bean.OperatorBean;

import java.util.List;

public interface OperatorMapper {

    List<OperatorBean> selectOperator(OperatorBean operatorBean);

    OperatorBean selectOrgById(String userId);

    int selectOperatorByTel(String telNum);

    int updateOperator(OperatorBean operatorBean);

    int updateOperatorInfo(OperatorBean operatorBean);

    int updateOperatorById(String userId);

    int insertOperator(OperatorBean operatorBean);

    int insertOperatorInfo(OperatorBean operatorBean);

    Integer countOperator(String orgId);

}
