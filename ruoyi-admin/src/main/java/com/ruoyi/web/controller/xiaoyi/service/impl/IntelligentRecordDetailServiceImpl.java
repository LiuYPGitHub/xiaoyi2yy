package com.ruoyi.web.controller.xiaoyi.service.impl;

import com.ruoyi.web.controller.xiaoyi.dao.IntelligentRecordDetailMapper;
import com.ruoyi.web.controller.xiaoyi.domain.IntelligentRecordDetail;
import com.ruoyi.web.controller.xiaoyi.service.IntelligentRecordDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author CJ
 * @date 2019/11/4
 */
@Transactional
@Service
public class IntelligentRecordDetailServiceImpl implements IntelligentRecordDetailService {

    @Autowired
    private IntelligentRecordDetailMapper intelligentRecordDetailMapper;

    @Override
    public List<IntelligentRecordDetail> queryByCourseId(Integer courseId) {
        return intelligentRecordDetailMapper.selectById(courseId);
    }

}
