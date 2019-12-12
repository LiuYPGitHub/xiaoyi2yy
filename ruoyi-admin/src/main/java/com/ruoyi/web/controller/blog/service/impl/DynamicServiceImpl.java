package com.ruoyi.web.controller.blog.service.impl;

import com.ruoyi.web.controller.blog.bean.DynamicBean;
import com.ruoyi.web.controller.blog.bean.DynamicDetail;
import com.ruoyi.web.controller.blog.dao.DynamicMapper;
import com.ruoyi.web.controller.blog.service.DynamicService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description：发布
 * @Author：kk
 * @Date：2019/9/18 11:35
 */
@Service
public class DynamicServiceImpl implements DynamicService {

    private @Autowired
    DynamicMapper dynamicMapper;
    private @Autowired
    Mapper mapper;

    @Override
    public List<DynamicBean> getDynamic(DynamicBean dynamicBean) {
        final List<DynamicBean> dynamicBeans = dynamicMapper.selectDynamic(dynamicBean);
        return dynamicBeans;
    }

    @Override
    public DynamicDetail getDynamicDetail(Long dynaId) {
        final DynamicBean dynamicBean = dynamicMapper.selectDynamicDetail(dynaId);

        final List<String> stringList = dynamicMapper.selectDynamicImg(dynaId);
        final DynamicDetail map = mapper.map(dynamicBean, DynamicDetail.class);
        map.setDynaImgs(stringList);
        return map;
    }
}
