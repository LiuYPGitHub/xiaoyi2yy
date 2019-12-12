package com.ruoyi.web.controller.blog.service.impl;

import com.ruoyi.web.controller.blog.dao.CourseMapper;
import com.ruoyi.web.controller.blog.domain.Course;
import com.ruoyi.web.controller.blog.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 案例信息
 */
@Service
public class CourseServiceImpl implements CourseService
{
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 信息集合
     */
    @Override
    public List<Course> selectCourseList(Course course) {
        return courseMapper.selectCourseList(course);
    }

    @Override
    public Course selectDetailedById(String coId)
    {
        return courseMapper.selectDetailedById(coId);
    }
}
