package com.ruoyi.web.controller.user.dao;

import com.ruoyi.web.controller.user.bean.CooperationBean;

import java.util.List;

public interface CooperationMapper {

    /**
     * 合作列表
     *
     * @return
     */
    List<CooperationBean> selectCooperation(CooperationBean cooperationBean);

    /**
     * 详情
     * @param cooperationId
     * @return
     */
    CooperationBean selectCooperationDetail(String cooperationId);
}
