package com.ruoyi.web.controller.xiaoyi.service;

import com.ruoyi.web.controller.xiaoyi.domain.RecordCourseDetail;

import java.util.List;

public interface RecordCourseDetailService {

    List<RecordCourseDetail> queryRecordCourseDetailByCourseId(Integer courseId);

}
