package com.ruoyi.web.controller.user.service.impl;

import com.ruoyi.web.controller.common.enums.CooperationStatus;
import com.ruoyi.web.controller.common.enums.SexStatus;
import com.ruoyi.web.controller.user.bean.CooperationBean;
import com.ruoyi.web.controller.user.dao.CooperationMapper;
import com.ruoyi.web.controller.user.service.CooperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description：合作列表
 * @Author：kk
 * @Date：2019/9/25 17:53
 */
@Service
public class CooperationServiceImpl implements CooperationService {

    private @Autowired
    CooperationMapper cooperationMapper;

    @Override
    public List<CooperationBean> getCooperationBeanList(CooperationBean cooperationBean) {
        final List<CooperationBean> cooperationBeanList = cooperationMapper.selectCooperation(cooperationBean);
        return cooperationBeanList;
    }

    @Override
    public CooperationBean getCoopDetail(String cooperationId) {
        final CooperationBean cooperationBean = cooperationMapper.selectCooperationDetail(cooperationId);
        cooperationBean.setTypeName(CooperationStatus.getInfo(cooperationBean.getType()));
        cooperationBean.setSex(SexStatus.getInfo(cooperationBean.getSex()));
        return cooperationBean;
    }
}
