package com.ruoyi.web.controller.xiaoyi.controller;

import com.ruoyi.web.controller.xiaoyi.service.TimedTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 校翼定时任务接口
 * @author CJ
 * @date 2019/10/15
 */
@Api(tags = {"校翼定时任务接口"})
@Configuration
@EnableScheduling   // 2.开启定时任务
@Slf4j
@Controller
public class TimedTaskController {

    @Autowired
    private TimedTaskService timedTaskService;

    @RequestMapping(path = "/xiaoyi/liveCourse/liveCourseStartTasks",method = RequestMethod.GET)
    @ApiOperation(value = "推送直播课程至添翼申学",notes = "推送直播课程至添翼申学")
    @Scheduled(cron = "0 0 0 * * ?")
    @ResponseBody
    public String sendLiveCourse() throws Exception {
        return timedTaskService.sendLiveCourse();
    }

    @RequestMapping(path = "/xiaoyi/liveCourse/checkCourseStatus",method = RequestMethod.GET)
    @ApiOperation(value = "获取添翼申学最新课程状态",notes = "获取添翼申学最新课程状态")
    @Scheduled(cron = "0 0 2 * * ?")
    @ResponseBody
    public String checkCourseStatus(){
        return timedTaskService.checkCourseStatus();
    }

    @RequestMapping(path = "/xiaoyi/liveCourse/startUpdateCourseOrder",method = RequestMethod.GET)
    @ApiOperation(value = "更新课程订单数据",notes = "更新课程订单数据")
    @Scheduled(cron = "0 0 4 * * ?")
    @ResponseBody
    public String updateCourseOrder(){
        return timedTaskService.updateCourseOrder();
    }

    @RequestMapping(path = "/xiaoyi/polyWei/pistonFlow",method = RequestMethod.GET)
    @ApiOperation(value = "开始、结束保利威推流",notes = "开始、结束保利威推流")
    @Scheduled(cron = "0 0/5 * * * ?")
    @ResponseBody
    public String pistonFlow(){
        return timedTaskService.pistonFlow();
    }

}
