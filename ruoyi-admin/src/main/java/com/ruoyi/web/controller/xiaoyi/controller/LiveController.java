package com.ruoyi.web.controller.xiaoyi.controller;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.web.controller.common.annotation.ShowLogger;
import com.ruoyi.web.controller.xiaoyi.domain.LiveCourse;
import com.ruoyi.web.controller.xiaoyi.domain.LiveCourseDetail;
import com.ruoyi.web.controller.xiaoyi.service.XiaoYiCourseDetailService;
import com.ruoyi.web.controller.xiaoyi.service.XiaoYiCourseService;
import com.ruoyi.web.controller.xiaoyi.utils.HtmlUtil;
import com.ruoyi.web.controller.xiaoyi.utils.JsoupUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 直播
 * @author CJ
 * @date 2019/10/12
 */
@Controller
@Api(tags = {"添翼申学直播课数据接口"})
public class LiveController extends BaseController {

    @Autowired
    private XiaoYiCourseService xiaoYiCourseService;
    @Autowired
    private XiaoYiCourseDetailService xiaoYiCourseDetailService;

    @ShowLogger(info = "校翼-直播课页面")
    @ApiOperation(value = "校翼-直播课页面",notes = "校翼-直播课页面")
    @RequestMapping(path = "/xiaoyi/live/index",method = RequestMethod.GET)
    public String index(){
        return "xiaoyi/live/index";
    }

    @ApiOperation(value = "校翼-直播课列表",notes = "校翼-直播课列表")
    @ShowLogger(info = "校翼-直播课列表")
    @RequestMapping(path = "/xiaoyi/live/list",method = RequestMethod.POST)
    @ResponseBody
    public TableDataInfo classList(LiveCourse course){
        startPage();
        List<LiveCourse> courses = xiaoYiCourseService.selectByLike(course);
        xiaoYiCourseService.processData(courses);
        return getDataTable(courses);
    }

    @ShowLogger(info = "校翼-查询单个直播课数据")
    @ApiOperation(value = "校翼-查询单个直播课数据",notes = "校翼-查询单个直播课数据")
    @RequestMapping(path = "/xiaoyi/live/detailed/{id}",method = RequestMethod.GET)
    public String classById(@PathVariable(value = "id") Integer id, Model model){
        LiveCourse course = xiaoYiCourseService.queryById(id);
        //未显示图片替换成☆
        HtmlUtil.processCourseHtml(course);
        course.setDesc(JsoupUtil.replaceDesc(course.getDesc()));
        List<LiveCourseDetail> liveCourseDetails = xiaoYiCourseDetailService.queryCourseById(course.getId());
        model.addAttribute("liveCourse",course);
        model.addAttribute("liveCourseDetail", liveCourseDetails);
        return "xiaoyi/live/detailed";
    }

    @ShowLogger(info = "校翼-编辑单个直播课数据")
    @ApiOperation(value = "校翼-编辑单个直播课数据",notes = "校翼-编辑单个直播课数据")
    @RequestMapping(path = "/xiaoyi/live/edit/{id}",method = RequestMethod.GET)
    public String editById(@PathVariable(value = "id") Integer id, Model model){
        LiveCourse course = xiaoYiCourseService.queryById(id);
        model.addAttribute("liveCourse",course);
        return "xiaoyi/live/edit";
    }

    @ResponseBody
    @ShowLogger(info = "校翼-保存单个直播课程状态")
    @ApiOperation(value = "校翼-保存单个直播课程状态",notes = "校翼-保存单个直播课程状态")
    @RequestMapping(path = "/xiaoyi/live/edit",method = RequestMethod.POST)
    public AjaxResult editSave(@Validated LiveCourse course) {
        return toAjax(xiaoYiCourseService.updateCourse(course));
    }

}