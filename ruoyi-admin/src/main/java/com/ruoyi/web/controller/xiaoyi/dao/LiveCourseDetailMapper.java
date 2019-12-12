package com.ruoyi.web.controller.xiaoyi.dao;

import com.ruoyi.web.controller.xiaoyi.domain.LiveCourseDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LiveCourseDetailMapper {

    List<LiveCourseDetail> selectByCourseId(@Param(value = "courseId") Integer courseId);

    int updateXiaoYiCourseDetail(LiveCourseDetail detail);

}
