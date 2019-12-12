package com.ruoyi.web.controller.xiaoyi.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.web.controller.common.annotation.ShowLogger;
import com.ruoyi.web.controller.common.videoApi.QueryVideoTokenAPI;
import com.ruoyi.web.controller.common.videoApi.QueryVideoTokenRequest;
import com.ruoyi.web.controller.common.videoApi.QueryVideoTokenResponse;
import com.ruoyi.web.controller.xiaoyi.domain.RecordCourse;
import com.ruoyi.web.controller.xiaoyi.domain.RecordCourseDetail;
import com.ruoyi.web.controller.xiaoyi.service.RecordCourseDetailService;
import com.ruoyi.web.controller.xiaoyi.service.RecordCourseService;
import com.ruoyi.web.controller.xiaoyi.utils.HtmlUtil;
import com.ruoyi.web.controller.xiaoyi.utils.JsoupUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 录播
 * 添翼申学录播数据接口
 * @author CJ
 * @date 2019/11/5
 */
@Controller
@Api(tags = {"添翼申学录播课数据接口"})
public class RecordController extends BaseController {

    @Autowired
    private RecordCourseDetailService recordCourseDetailService;
    @Autowired
    private RecordCourseService recordCourseService;
    @Autowired
    private QueryVideoTokenAPI queryVideoTokenAPI;

    @ShowLogger(info = "校翼-录播课页面")
    @ApiOperation(value = "校翼-录播课页面",notes = "校翼-录播课页面")
    @RequestMapping(path = "/xiaoyi/record/index",method = RequestMethod.GET)
    public String index(){
        return "xiaoyi/record/index";
    }

    @ApiOperation(value = "校翼-录播课列表",notes = "校翼-录播课列表")
    @ShowLogger(info = "校翼-录播课列表")
    @RequestMapping(path = "/xiaoyi/record/list",method = RequestMethod.POST)
    @ResponseBody
    public TableDataInfo classList(RecordCourse recordCourse){
        startPage();
        List<RecordCourse> recordCourses = recordCourseService.queryRecordCourseList(recordCourse);
        recordCourseService.processData(recordCourses);
        return getDataTable(recordCourses);
    }

    @ShowLogger(info = "校翼-查询单个录播课数据")
    @ApiOperation(value = "校翼-查询单个录播课数据",notes = "校翼-查询单个录播课数据")
    @RequestMapping(path = "/xiaoyi/record/detailed/{id}",method = RequestMethod.GET)
    public String classById(@PathVariable(value = "id") Integer id, Model model){
        RecordCourse recordCourse = recordCourseService.queryById(id);
        //未显示图片替换成☆
        HtmlUtil.processCourseHtml(recordCourse);
        recordCourse.setDesc(JsoupUtil.replaceDesc(recordCourse.getDesc()));
        List<RecordCourseDetail> recordCourseDetails = recordCourseDetailService.queryRecordCourseDetailByCourseId(recordCourse.getId());
        model.addAttribute("recordCourse",recordCourse);
        model.addAttribute("recordCourseDetail",recordCourseDetails);
        return "xiaoyi/record/detailed";
    }

    @ShowLogger(info = "校翼-编辑单个录播课数据")
    @ApiOperation(value = "校翼-编辑单个录播课数据",notes = "校翼-编辑单个录播课数据")
    @RequestMapping(path = "/xiaoyi/record/edit/{id}",method = RequestMethod.GET)
    public String editById(@PathVariable(value = "id") Integer id, Model model){
        RecordCourse recordCourse = recordCourseService.queryById(id);
        model.addAttribute("recordCourse",recordCourse);
        return "xiaoyi/record/edit";
    }

    @ResponseBody
    @ShowLogger(info = "校翼-保存单个录播课程状态")
    @ApiOperation(value = "校翼-保存单个录播课程状态",notes = "校翼-保存单个录播课程状态")
    @RequestMapping(path = "/xiaoyi/record/edit",method = RequestMethod.POST)
    public AjaxResult editSave(@Validated RecordCourse recordCourse) {
        return toAjax(recordCourseService.updateCourse(recordCourse));
    }

    @ShowLogger(info = "校翼-播放录播课程视频")
    @ApiOperation(value = "校翼-播放录播课程视频",notes = "校翼-播放录播课程视频")
    @RequestMapping(path = "/xiaoyi/record/player/{vid}",method = RequestMethod.GET)
    public String player(@PathVariable String vid,Model model) {
        String token;
        QueryVideoTokenResponse response = queryVideoTokenAPI.process(new QueryVideoTokenRequest(vid));
        if(!StringUtils.isEmpty(response)&&!StringUtils.isEmpty(response.getApiToken())&&!StringUtils.isEmpty(response.getApiToken().getToken())){
            token = response.getApiToken().getToken();
        }else{
            token = null;
        }
        model.addAttribute("vid",vid);
        model.addAttribute("token",token);
        return "xiaoyi/record/player";
    }

}
