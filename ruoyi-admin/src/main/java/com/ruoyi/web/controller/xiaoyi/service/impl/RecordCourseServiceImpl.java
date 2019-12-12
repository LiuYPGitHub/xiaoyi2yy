package com.ruoyi.web.controller.xiaoyi.service.impl;

import com.ruoyi.web.controller.xiaoyi.dao.IntelligentRecordMapper;
import com.ruoyi.web.controller.xiaoyi.dao.RecordCourseMapper;
import com.ruoyi.web.controller.xiaoyi.domain.RecordCourse;
import com.ruoyi.web.controller.xiaoyi.service.RecordCourseService;
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
public class RecordCourseServiceImpl implements RecordCourseService {

    @Autowired
    private RecordCourseMapper recordCourseMapper;
    @Autowired
    private IntelligentRecordMapper intelligentRecordMapper;

    /**
     * 处理录播显示数据
     * @param courses
     */
    @Override
    public void processData(List<RecordCourse> courses) {
        for (RecordCourse record : courses) {
            String orgCode = record.getOrgCode();
            /*String createName = intelligentRecordMapper.getCreateName(orgCode);
            record.setCreateName(createName);*/
            String siteName = intelligentRecordMapper.getSiteName(orgCode);
            record.setSiteName(siteName);
        }
    }

    @Override
    public List<RecordCourse> queryRecordCourseList(RecordCourse recordCourse) {
        return recordCourseMapper.selectByLike(recordCourse);
    }

    @Override
    public RecordCourse queryById(Integer id) {
        return recordCourseMapper.selectById(id);
    }

    @Override
    public int updateCourse(RecordCourse recordCourse) {
        return recordCourseMapper.updateCourse(recordCourse);
    }

}
