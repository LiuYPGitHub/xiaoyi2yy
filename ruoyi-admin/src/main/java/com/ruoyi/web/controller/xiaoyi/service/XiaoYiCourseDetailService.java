package com.ruoyi.web.controller.xiaoyi.service;

import com.ruoyi.web.controller.xiaoyi.domain.LiveCourseDetail;

import java.util.List;

/**
 * @author CJ
 * @date 2019/10/14
 */
public interface XiaoYiCourseDetailService {

    List<LiveCourseDetail> queryCourseById(Integer courseId);

    int updateXiaoYiCourseDetail(LiveCourseDetail detail);

}
