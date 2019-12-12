package com.ruoyi.web.controller.xiaoyi.service;

public interface TimedTaskService {

    /**
     * 推送直播课程至添翼申学
     * @return 推送结果
     * @throws Exception
     */
    String sendLiveCourse() throws Exception;

    /**
     * 检查添翼申学是否已存在该课程
     * @return 检查结果
     */
    String checkCourseStatus();

    /**
     * 更新课程订单数据
     * @return 更新结果
     */
    String updateCourseOrder();


    /**
     * 开始、结束保利威推流
     * @return 结果 true:成功 false:失败
     */
    String pistonFlow();

}
