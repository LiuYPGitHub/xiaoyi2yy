package com.ruoyi.web.controller.user.service;

import com.ruoyi.web.controller.user.bean.OperatorBean;

import java.util.List;

public interface OperatorService {

    /**
     * 机构运营 列表
     *
     * @param operatorBean
     * @return
     */
    List<OperatorBean> listOperator(OperatorBean operatorBean);

    /**
     * 详情
     *
     * @param orgId
     * @return
     */
    OperatorBean getOrg(String orgId);

    /**
     * 详情
     *
     * @param operatorId
     * @return
     */
    OperatorBean getOperator(String operatorId);

    /**
     * 校验手机号 唯一
     *
     * @param telNum
     * @return
     */
    Boolean checkOperator(String telNum);

    /**
     * 保存 机构运营人员
     */
    int saveOperator(OperatorBean operatorBean);

    /**
     * 修改 机构运营人员
     *
     * @param operatorBean
     * @return
     */
    int updateOperator(OperatorBean operatorBean);

    /**
     * 启禁用
     *
     * @param operatorBean
     * @return
     */
    int changeStatus(OperatorBean operatorBean);

    /**
     * 删除 机构运营人员
     *
     * @param operatorId
     * @return
     */
    int deleteOperator(String operatorId);

    int operatorCount();
}
