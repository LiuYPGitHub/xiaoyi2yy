package com.ruoyi.web.controller.blog.service.impl;

import com.ruoyi.web.controller.blog.dao.EventMapper;
import com.ruoyi.web.controller.blog.domain.Case;
import com.ruoyi.web.controller.blog.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 案例信息
 */
@Service
public class EventServiceImpl implements EventService
{
    @Autowired
    private EventMapper eventMapper;

    /**
     * 信息集合
     */
    @Override
    public List<Case> selectCaseList(Case casee) {
        return eventMapper.selectCaseList(casee);
    }

    @Override
    public Case selectDetailedById(String evtId)
    {
        return eventMapper.selectDetailedById(evtId);
    }
}
