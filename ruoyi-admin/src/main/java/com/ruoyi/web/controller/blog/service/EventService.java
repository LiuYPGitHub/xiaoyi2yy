package com.ruoyi.web.controller.blog.service;

import com.ruoyi.web.controller.blog.domain.Case;

import java.util.List;

/**
 * 案例
 */
public interface EventService
{
    /**
     * 信息集合
     */
    List<Case> selectCaseList(Case casee);

    Case selectDetailedById(String evtId);
}
