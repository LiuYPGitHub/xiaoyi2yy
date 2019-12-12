package com.ruoyi.web.controller.xiaoyi.controller;

import com.ruoyi.web.controller.common.annotation.ShowLogger;
import com.ruoyi.web.controller.common.xiaoyiApi.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 添翼申学API调用示例接口
 * @author CJ
 * @date 2019/10/12
 */
@Controller
@Api(tags = {"添翼申学API调用示例接口"})
public class XiaoYiApiController {

    @Autowired
    private AddTeacherAPI addTeacherAPI;
    @Autowired
    private AddLiveCourseAPI addLiveCourseAPI;
    @Autowired
    private AddLiveLessonAPI addLiveLessonAPI;
    @Autowired
    private QueryLiveCourseDetailAPI queryLiveCourseDetailAPI;
    @Autowired
    private QueryLiveCourseOrderAPI queryLiveCourseOrderAPI;
    @Autowired
    private AddRecordCourseAPI addRecordCourseAPI;
    @Autowired
    private AddRecordLessonAPI addRecordLessonAPI;
    @Autowired
    private AddUserCourseAPI addUserCourseAPI;

    @ShowLogger(info = "添加教师")
    @ResponseBody
    @RequestMapping(path = "/api/addTeacher",method = RequestMethod.POST)
    @ApiOperation(value = "添加教师",notes = "添加教师")
    public AddTeacherResponse addTeacher(AddTeacherRequest request) throws Exception {
        return addTeacherAPI.process(request);
    }

    @ShowLogger(info = "添加直播课程")
    @ResponseBody
    @RequestMapping(path = "/api/addLiveCourse",method = RequestMethod.POST)
    @ApiOperation(value = "添加直播课程",notes = "添加直播课程")
    public AddLiveCourseResponse addLiveCourse(AddLiveCourseRequest request) throws Exception {
        return addLiveCourseAPI.process(request);
    }

    @ShowLogger(info = "添加直播课节")
    @ResponseBody
    @RequestMapping(path = "/api/addLiveLesson",method = RequestMethod.POST)
    @ApiOperation(value = "添加直播课节",notes = "添加直播课节")
    public AddLiveLessonResponse addLesson(AddLiveLessonRequest request) throws Exception {
        return addLiveLessonAPI.process(request);
    }

    @ShowLogger(info = "查询直播课程详情")
    @ResponseBody
    @RequestMapping(path = "/api/getLiveCourseDetail",method = RequestMethod.GET)
    @ApiOperation(value = "查询直播课程详情",notes = "查询直播课程详情")
    public QueryLiveCourseDetailResponse getLiveCourseDetail (QueryLiveCourseDetailRequest request) {
        return queryLiveCourseDetailAPI.process(request);
    }

    @ShowLogger(info = "查询直播课程订单")
    @ResponseBody
    @RequestMapping(path = "/api/getLiveCourseOrder",method = RequestMethod.GET)
    @ApiOperation(value = "查询直播课程订单",notes = "查询直播课程订单")
    public QueryLiveCourseOrderResponse getLiveCourseOrder(QueryLiveCourseOrderRequest request){
       return queryLiveCourseOrderAPI.process(request);
    }

    @ShowLogger(info = "添加录播课程")
    @ResponseBody
    @RequestMapping(path = "/api/addRecordCourse",method = RequestMethod.POST)
    @ApiOperation(value = "添加录播课程",notes = "添加录播课程")
    public AddRecordCourseResponse addRecordCourse(AddRecordCourseRequest request) throws Exception {
        return addRecordCourseAPI.process(request);
    }

    @ShowLogger(info = "添加录播课节")
    @ResponseBody
    @RequestMapping(path = "/api/addRecordLesson",method = RequestMethod.POST)
    @ApiOperation(value = "添加直播课节",notes = "添加直播课节")
    public AddRecordLessonResponse addLesson(AddRecordLessonRequest request) throws Exception {
        return addRecordLessonAPI.process(request);
    }

    @ShowLogger(info = "添加用户课节")
    @ResponseBody
    @RequestMapping(path = "/api/addUserCourse",method = RequestMethod.POST)
    @ApiOperation(value = "添加用户课节",notes = "添加用户课节")
    public AddUserCourseResponse addUserCourse(AddUserCourseRequest request) throws Exception {
        return addUserCourseAPI.process(request);
    }

}
