package com.ruoyi.web.controller.blog.service;

import com.ruoyi.web.controller.blog.domain.Leads;

import java.util.List;

/**
 * 案例
 */
public interface LeadsService {
    /**
     * 信息集合
     */
    List<Leads> selectLeadsList(Leads leads);

    Leads selectDetailedById(String leId);

}
