package com.ruoyi.web.controller.xiaoyi.service.impl;

import com.ruoyi.web.controller.xiaoyi.dao.RecordCourseDetailMapper;
import com.ruoyi.web.controller.xiaoyi.domain.RecordCourseDetail;
import com.ruoyi.web.controller.xiaoyi.service.RecordCourseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author CJ
 * @date 2019/11/5
 */
@Transactional
@Service
public class RecordCourseDetailServiceImpl implements RecordCourseDetailService {

    @Autowired
    private RecordCourseDetailMapper recordCourseDetailMapper;

    @Override
    public List<RecordCourseDetail> queryRecordCourseDetailByCourseId(Integer courseId) {
        return recordCourseDetailMapper.selectByCourseId(courseId);
    }

}
