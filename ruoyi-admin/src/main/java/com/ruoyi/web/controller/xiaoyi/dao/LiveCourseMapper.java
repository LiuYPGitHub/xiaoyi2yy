package com.ruoyi.web.controller.xiaoyi.dao;

import com.ruoyi.web.controller.xiaoyi.domain.LiveCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LiveCourseMapper {

    List<LiveCourse> selectCourse(LiveCourse course);

    List<Integer> getCourseIds();

    LiveCourse selectById(@Param(value = "id") Integer id);

    int updateCourse(LiveCourse course);

    String selectSiteNameByUserId(@Param(value = "phone") String phone);

    String getChannelName(@Param(value = "channel") String channel);

}
