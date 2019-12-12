package com.ruoyi.web.controller.xiaoyi.dao;

import com.ruoyi.web.controller.xiaoyi.domain.RecordCourseDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordCourseDetailMapper {

    List<RecordCourseDetail> selectByCourseId(@Param(value = "courseId")Integer courseId);

}
