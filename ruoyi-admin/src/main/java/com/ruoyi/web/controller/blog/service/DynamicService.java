package com.ruoyi.web.controller.blog.service;

import com.ruoyi.web.controller.blog.bean.DynamicBean;
import com.ruoyi.web.controller.blog.bean.DynamicDetail;

import java.util.List;

public interface DynamicService {

    List<DynamicBean> getDynamic(DynamicBean dynamicBean);

    DynamicDetail getDynamicDetail(Long dynaId);
}
