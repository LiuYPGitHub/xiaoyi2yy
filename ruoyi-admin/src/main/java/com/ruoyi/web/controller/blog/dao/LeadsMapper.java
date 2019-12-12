package com.ruoyi.web.controller.blog.dao;

import com.ruoyi.web.controller.blog.domain.Leads;

import java.util.List;

/**
 * 案例
 */
public interface LeadsMapper {
    /**
     * 数据集合
     */
    List<Leads> selectLeadsList(Leads leads);

    Leads selectDetailedById(String leId);

    Leads selectCode(String City);

    Leads selectLeads(String leId);
}
