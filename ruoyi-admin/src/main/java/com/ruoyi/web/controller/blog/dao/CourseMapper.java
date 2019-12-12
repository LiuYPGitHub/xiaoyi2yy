package com.ruoyi.web.controller.blog.dao;

import com.ruoyi.web.controller.blog.domain.Course;

import java.util.List;

/**
 * 案例
 */
public interface CourseMapper {
    /**
     * 数据集合
     */
    List<Course> selectCourseList(Course course);

    Course selectDetailedById(String coId);
}
