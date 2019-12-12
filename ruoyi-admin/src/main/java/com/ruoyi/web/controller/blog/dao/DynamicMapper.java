package com.ruoyi.web.controller.blog.dao;


import com.ruoyi.web.controller.blog.bean.DynamicBean;

import java.util.List;

/**
 * @Description：动态
 * @Author：kk
 * @Date：2019/9/18 10:46
 */
public interface DynamicMapper {

    List<DynamicBean> selectDynamic(DynamicBean dynamicBean);

    DynamicBean selectDynamicDetail(Long dynaId);

    List<String> selectDynamicImg(Long dynaId);

}
