package com.ruoyi.web.controller.xiaoyi.service;

import com.ruoyi.web.controller.xiaoyi.domain.RecordCourse;

import java.util.List;

public interface RecordCourseService {


    /**
     * 处理录播显示数据
     * @param courses
     */
    void processData(List<RecordCourse> courses);

    List<RecordCourse> queryRecordCourseList(RecordCourse recordCourse);

    RecordCourse queryById(Integer id);

    int updateCourse(RecordCourse recordCourse);

}
