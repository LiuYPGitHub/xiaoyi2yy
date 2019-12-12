package com.ruoyi.web.controller.blog.service.impl;

import com.ruoyi.web.controller.blog.dao.CaseMapper;
import com.ruoyi.web.controller.blog.domain.Case;
import com.ruoyi.web.controller.blog.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 案例信息
 */
@Service
public class CaseServiceImpl implements CaseService
{
    @Autowired
    private CaseMapper caseMapper;

    /**
     * 信息集合
     */
    @Override
    public List<Case> selectCaseList(Case casee) {
        return caseMapper.selectCaseList(casee);
    }

    @Override
    public Case selectDetailedById(String evtId)
    {
        return caseMapper.selectDetailedById(evtId);
    }
}
