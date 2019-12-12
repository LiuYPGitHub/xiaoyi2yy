package com.ruoyi.web.controller.xiaoyi.service;

import com.ruoyi.web.controller.xiaoyi.domain.IntelligentRecord;
import com.ruoyi.web.controller.xiaoyi.domain.LiveCourse;

import java.util.List;


public interface XiaoYiCourseService {

    /**
     * 处理直播显示的数据
     * @param courses
     */
    void processData(List<LiveCourse> courses);

    List<LiveCourse> selectByLike(LiveCourse course);

    List<Integer> getCourseIds();

    LiveCourse queryById(Integer id);

    int updateCourse(LiveCourse course);

    String getSiteNameByPhone(String phone);

    String getChannelName(String channel);

}
