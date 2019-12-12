package com.ruoyi.web.controller.blog.service;

import com.ruoyi.web.controller.blog.domain.Course;

import java.util.List;

/**
 * 案例
 */
public interface CourseService
{
    /**
     * 信息集合
     */
    List<Course> selectCourseList(Course course);

    Course selectDetailedById(String coId);

}
