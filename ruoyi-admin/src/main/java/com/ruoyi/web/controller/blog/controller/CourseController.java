package com.ruoyi.web.controller.blog.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.web.controller.blog.domain.Course;
import com.ruoyi.web.controller.blog.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程
 */
@Controller
@RequestMapping("/blog/course")
public class CourseController extends BaseController
{
    private String prefix = "blog/course";

    @Autowired
    private CourseService courseService;

//    @RequiresPermissions("blog:course:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/course";
    }

//    @RequiresPermissions("blog:course:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Course course) {
        startPage();
        List<Course> list = courseService.selectCourseList(course);
        return getDataTable(list);
    }

    @GetMapping("/detailed/{coId}")
//    @RequiresPermissions("blog:course:detailed")
    public String detail(@PathVariable("coId") String coId, ModelMap mmap) {
        mmap.put("Course", courseService.selectDetailedById(coId));
        return prefix + "/detailed";
    }
}
