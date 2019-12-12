package com.ruoyi.web.controller.blog.dao;

import com.ruoyi.web.controller.blog.domain.Case;

import java.util.List;

/**
 * 案例
 */
public interface EventMapper {
    /**
     * 数据集合
     */
    List<Case> selectCaseList(Case casee);

    Case selectDetailedById(String evtId);

}
