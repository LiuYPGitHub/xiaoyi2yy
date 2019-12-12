package com.ruoyi.web.controller.xiaoyi.dao;

import com.ruoyi.web.controller.xiaoyi.domain.RecordCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordCourseMapper {

    List<RecordCourse> selectByLike(RecordCourse recordCourse);

    RecordCourse selectById(@Param(value = "id")Integer id);

    int updateCourse(RecordCourse course);

}
