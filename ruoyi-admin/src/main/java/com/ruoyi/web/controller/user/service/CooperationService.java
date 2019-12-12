package com.ruoyi.web.controller.user.service;

import com.ruoyi.web.controller.user.bean.CooperationBean;

import java.util.List;

public interface CooperationService {

    /**
     * 合作列表
     *
     * @return
     */
    List<CooperationBean> getCooperationBeanList(CooperationBean cooperationBean);

    /**
     * 合作详情
     * @param cooperationId
     * @return
     */
    CooperationBean getCoopDetail(String cooperationId);
}
