package com.ruoyi.web.controller.xiaoyi.service.impl;

import com.ruoyi.web.controller.common.constant.APIConstant;
import com.ruoyi.web.controller.common.videoApi.*;
import com.ruoyi.web.controller.common.xiaoyiApi.*;
import com.ruoyi.web.controller.common.xiaoyiApi.bean.APICourseOrder;
import com.ruoyi.web.controller.xiaoyi.bean.IntelligentRecordBean;
import com.ruoyi.web.controller.xiaoyi.domain.LiveCourse;
import com.ruoyi.web.controller.xiaoyi.domain.LiveCourseDetail;
import com.ruoyi.web.controller.xiaoyi.domain.TaskLogger;
import com.ruoyi.web.controller.xiaoyi.domain.XiaoYiOrder;
import com.ruoyi.web.controller.xiaoyi.service.*;
import com.ruoyi.web.controller.xiaoyi.utils.DateUtil;
import com.ruoyi.web.controller.xiaoyi.utils.HtmlUtil;
import com.ruoyi.web.controller.xiaoyi.utils.TimesTaskUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 定时任务实现
 * @author CJ
 * @date 2019/10/16
 */
@Transactional
@Slf4j
@Component
public class TimedTaskServiceImpl implements TimedTaskService {

    @Autowired
    private XiaoYiCourseDetailService xiaoYiCourseDetailService;
    @Autowired
    private IntelligentRecordService intelligentRecordService;
    @Autowired
    private QueryLiveCourseDetailAPI queryLiveCourseDetailAPI;
    @Autowired
    private QueryLiveCourseOrderAPI queryLiveCourseOrderAPI;
    @Autowired
    private XiaoYiTeacherService xiaoYiTeacherService;
    @Autowired
    private XiaoYiCourseService xiaoYiCourseService;
    @Autowired
    private XiaoYiOrderService xiaoYiOrderService;
    @Autowired
    private StartPistonFlowAPI startPistonFlowAPI;
    @Autowired
    private StopPistonFlowAPI stopPistonFlowAPI;
    @Autowired
    private TaskLoggerService taskLoggerService;
    @Autowired
    private AddLiveCourseAPI addLiveCourseAPI;
    @Autowired
    private AddLiveLessonAPI addLiveLessonAPI;
    @Autowired
    private AddTeacherAPI addTeacherAPI;

    //已通过课程状态编号
    private static final Integer status=5;
    //已上架课程状态编号
    private static final Integer successStatus=4;
    //是否使用用户的机构名称
    private static final boolean isUserSiteNameFlag=false;

    /**
     * 推送直播课程至添翼申学
     * @return 推送结果
     */
    @Override
    public String sendLiveCourse() throws Exception {
        long startTime = System.currentTimeMillis();
        //try{
            log.info("------------------------- 开始执行上传课程信息定时任务 -------------------------");
            //获取内部审核通过的直播课程
            LiveCourse liveCourse =new LiveCourse();
            liveCourse.setStatus(new Integer(status));
            List<LiveCourse> courses = xiaoYiCourseService.selectByLike(liveCourse);
            for (LiveCourse cours : courses) {
                log.info("开始更新["+cours.getCourseName()+"]直播课程信息");
                log.info("正在获取["+cours.getCourseName()+"]机构信息");
                String siteName;
                if(isUserSiteNameFlag){
                    siteName = xiaoYiCourseService.getSiteNameByPhone(cours.getAccount());
                }
                log.info("正在添加["+cours.getCourseName()+"]直播课程教师信息");
                //添加教师
                AddTeacherResponse addTeacherResult = addTeacherAPI.process(new AddTeacherRequest(cours.getAccount(), APIConstant.API_SITENAMEVALUE));
                String resultTyTeacherId = addTeacherResult.getTeacherId();
                //根据内部教师编号查询teacher教师信息、修改ty_teacher_id
                String id = xiaoYiTeacherService.queryIdByMobile(cours.getAccount());
                if(!StringUtils.isEmpty(id)){
                    log.info("正在修改["+cours.getCourseName()+"]添翼教师信息");
                    xiaoYiTeacherService.updateTeacherId(resultTyTeacherId,id);
                }
                cours.setTeacherId(resultTyTeacherId);
                log.info("已添加["+cours.getCourseName()+"]直播课程教师信息");
                log.info("正在添加["+cours.getOrgCode()+"]直播课程信息");
                log.info("正在添加["+cours.getCourseName()+"]直播课程信息");
                //添加直播课程
                if(StringUtils.isEmpty(cours.getClassId())){
                    AddLiveCourseRequest request=new AddLiveCourseRequest();
                    if(isUserSiteNameFlag) {
                        request.setSiteName(siteName);
                    }
                    request.setShowEvaluation(cours.getShowEvaluation().toString());
                    request.setExpirationDuration(cours.getExpirationDuration());
                    request.setName(cours.getCourseName());
                    request.setClassHour(cours.getClassHour());
                    request.setClassType(cours.getClassType());
                    request.setCost(cours.getPrice().toString());
                    request.setPrimeCost(cours.getPrice().toString());
                    request.setCoverImgLink(cours.getCoverImg());
                    request.setEnrollStartDate(DateFormatUtils.format(cours.getEnrollStartDate(),"yyyyMMddHHmmss"));
                    request.setEnrollEndDate(DateFormatUtils.format(cours.getEnrollEndDate(),"yyyyMMddHHmmss"));

                    request.setCourseIntroduceImg(cours.getCourseIntroduceImg());
                    //课程信息
                    String desc = HtmlUtil.getCourseInfo(1,cours.getClassDifficult(), cours.getClassInfo(), cours.getCourseObj(), cours.getTextBook());
                    request.setCourseInformation(desc);
                    //师资介绍
                    String teacherInfo = HtmlUtil.getTeacherInfo(cours.getTeacherInfo(),cours.getTeacherName(),cours.getTeacherImgage());
                    request.setCourseTeachersHighlight(teacherInfo);
                    //课程亮点
                    String courseFeature = HtmlUtil.getCourseFeatureInfo(cours.getCourseFeature());
                    request.setCourseHighlight(courseFeature);
                    //学习内容
                    String contentInfo = HtmlUtil.getCourseContentInfo(cours.getCourseContent());
                    request.setCourseLearningContent(contentInfo);
                    //观看方式
                    String viewTypeInfo = HtmlUtil.getViewTypeInfo();
                    request.setCourseObservationStyle(viewTypeInfo);
                    //课程咨询
                    String consultantInfo = HtmlUtil.getCourseConsultantInfo();
                    request.setCourseConsultant(consultantInfo);
                    //温馨提示
                    String coursePromptInfo = HtmlUtil.getCoursePromptInfo();
                    request.setCourseWarmPrompt(coursePromptInfo);
                    request.setMaxNum("100");
                    AddLiveCourseResponse addCourseResult = addLiveCourseAPI.process(request);
                    String classId = addCourseResult.getClassId();
                    cours.setClassId(classId);
                    log.info("已添加["+cours.getCourseName()+"]直播课程信息");
                }else{
                    log.info("已存在["+cours.getCourseName()+"]直播课程信息");
                }
                log.info("正在获取["+cours.getCourseName()+"]直播课节信息");
                List<LiveCourseDetail> details = xiaoYiCourseDetailService.queryCourseById(cours.getId());
                for (LiveCourseDetail detail : details) {
                    log.info("已获取直播课程["+cours.getCourseName()+"] 课节["+detail.getLessonName()+"]信息");
                    if(StringUtils.isEmpty(detail.getLessonId())){
                        AddLiveLessonRequest addLiveLessonRequest =new AddLiveLessonRequest();
                        addLiveLessonRequest.setClassId(cours.getClassId());
                        //tyTeacherId = xiaoYiTeacherService.getTyTeacherId(cours.getTeacherId());
                        addLiveLessonRequest.setTeacherId(resultTyTeacherId);
                        addLiveLessonRequest.setLessonHour(detail.getLessonNum().toString());
                        addLiveLessonRequest.setLiveContent(detail.getLessonName());
                        //String yyyyMMdd = DateFormatUtils.format(detail.getLessonDate(), "yyyyMMdd");
                        String startDate = DateFormatUtils.format(detail.getStartTime(), "yyyyMMddHHmmss");
                        String endDate = DateFormatUtils.format(detail.getEndTime(), "yyyyMMddHHmmss");
                        addLiveLessonRequest.setLiveStartDate(startDate);
                        addLiveLessonRequest.setLiveEndDate(endDate);
                        addLiveLessonRequest.setLiveManNumber("1");
                        log.info("正在添加直播课程["+cours.getCourseName()+"] 课节["+detail.getLessonName()+"]信息");
                        AddLiveLessonResponse addLessonResult = addLiveLessonAPI.process(addLiveLessonRequest);
                        String lessonId = addLessonResult.getLessonId();
                        detail.setLessonId(lessonId);
                        xiaoYiCourseDetailService.updateXiaoYiCourseDetail(detail);
                        log.info("已添加直播课程["+cours.getCourseName()+"] 课节["+detail.getLessonName()+"]信息");
                    }else{
                        log.info("已存在直播课程["+cours.getCourseName()+"] 课节["+detail.getLessonName()+"]信息");
                    }
                }
                xiaoYiCourseService.updateCourse(cours);
                TaskLogger taskLogger=new TaskLogger();
                taskLogger.setResult("成功");
                taskLogger.setTaskName("上传"+cours.getCourseName()+"课程信息");
                taskLogger.setCreatedAt(new Date());
                taskLogger.setUpdatedAt(new Date());
                taskLoggerService.saveTaskLogger(taskLogger);
            }
        /*}catch (Exception e){
            log.info("------------------------- 上传课程信息定时任务执行失败 -------------------------");
            log.info("异常信息:"+e.getMessage());
            TaskLogger taskLogger=new TaskLogger();
            taskLogger.setResult("失败");
            taskLogger.setError(e.getMessage());
            taskLogger.setTaskName(new Date()+"课程信息");
            taskLogger.setCreatedAt(new Date());
            taskLogger.setUpdatedAt(new Date());
            taskLoggerService.saveTaskLogger(taskLogger);
            return "失败";
        }*/
        long endTime = System.currentTimeMillis();
        String result = TimesTaskUtil.process(startTime, endTime);
        log.info("------------------------- 上传课程信息定时任务执行完毕 -------------------------");
        log.info(result);
        return result;
    }

    /**
     * 根据添翼申学状态更新内部课程数据
     * @return 检查结果
     */
    @Override
    public String checkCourseStatus() {
        long startTime = System.currentTimeMillis();
        //try {
            log.info("------------------------- 开始执行更新课程状态信息定时任务 -------------------------");
            //获取内部审核通过的直播课程
            LiveCourse liveCourse =new LiveCourse();
            liveCourse.setStatus(new Integer(status));
            List<LiveCourse> courses = xiaoYiCourseService.selectByLike(liveCourse);
            for (LiveCourse cours : courses) {
                log.info("已获取["+cours.getCourseName()+"]课程信息");
                if(!StringUtils.isEmpty(cours.getClassId())){
                    QueryLiveCourseDetailRequest request=new QueryLiveCourseDetailRequest();
                    request.setClassId(cours.getClassId());
                    QueryLiveCourseDetailResponse detailResponse = queryLiveCourseDetailAPI.process(request);
                    log.info("正在获取["+cours.getCourseName()+"]课程状态信息");
                    if(!StringUtils.isEmpty(detailResponse)&&!StringUtils.isEmpty(detailResponse.getCourse())&&!StringUtils.isEmpty(detailResponse.getCourse().getIsSearch())){
                        Integer isSearch = detailResponse.getCourse().getIsSearch();
                        log.info("已获取["+cours.getCourseName()+"]课程状态信息 status:"+isSearch);
                        if(isSearch == 0){
                            //已下架
                            cours.setStatus(new Integer(3));
                            xiaoYiCourseService.updateCourse(cours);
                            log.info("已更新["+cours.getCourseName()+"]课程为下架状态");
                        }else if (isSearch == 1){
                            //已上架
                            cours.setStatus(new Integer(4));
                            xiaoYiCourseService.updateCourse(cours);
                            log.info("已更新["+cours.getCourseName()+"]课程为上架状态");
                        }
                        TaskLogger taskLogger=new TaskLogger();
                        taskLogger.setResult("成功");
                        taskLogger.setTaskName("更新"+cours.getCourseName()+"课程状态信息");
                        taskLogger.setCreatedAt(new Date());
                        taskLogger.setUpdatedAt(new Date());
                        taskLoggerService.saveTaskLogger(taskLogger);
                    }
                }
            }
       /* }catch (Exception e){
            log.info("------------------------- 更新课程状态信息定时任务执行失败 -------------------------");
            log.info("异常信息:"+e.getMessage());
            TaskLogger taskLogger=new TaskLogger();
            taskLogger.setResult("失败");
            taskLogger.setError(e.getMessage());
            taskLogger.setTaskName(new Date()+"课程状态信息");
            taskLogger.setCreatedAt(new Date());
            taskLogger.setUpdatedAt(new Date());
            taskLoggerService.saveTaskLogger(taskLogger);
            return "失败";
        }*/
        long endTime = System.currentTimeMillis();
        String result = TimesTaskUtil.process(startTime, endTime);
        log.info("------------------------- 更新课程状态信息定时任务执行完毕 -------------------------");
        log.info(result);
        return result;
    }

    /**
     * 更新/添加课程订单信息
     * @return 更新/添加结果
     */
    @Override
    public String updateCourseOrder() {
        long startTime = System.currentTimeMillis();
        //try {
            log.info("------------------------- 开始执行更新课程订单定时任务 -------------------------");
            //获取内部审核通过的直播课程
            LiveCourse liveCourse =new LiveCourse();
            liveCourse.setStatus(new Integer(successStatus));
            List<LiveCourse> courses = xiaoYiCourseService.selectByLike(liveCourse);
            for (LiveCourse cours : courses) {
                log.info("已获取["+cours.getCourseName()+"]课程信息");
                //if(!StringUtils.isEmpty(cours.getClassId())){
                    log.info("正在获取["+cours.getCourseName()+"]课程订单信息");
                    QueryLiveCourseOrderRequest request=new QueryLiveCourseOrderRequest();
                    request.setClassId(cours.getClassId());
                    QueryLiveCourseOrderResponse orderResponse = queryLiveCourseOrderAPI.process(request);
                    if(!StringUtils.isEmpty(orderResponse)&&!StringUtils.isEmpty(orderResponse.getOrders())&&orderResponse.getOrders().size()>0){
                        log.info("已获取["+cours.getCourseName()+"]课程订单信息");
                        List<APICourseOrder> orders = orderResponse.getOrders();
                        for (APICourseOrder courseOrder : orders) {
                            XiaoYiOrder order=new XiaoYiOrder();
                            order.setCourseName(cours.getCourseName());
                            order.setPayType(courseOrder.getPayType());
                            order.setRefundName(courseOrder.getRefundName());
                            if(courseOrder.getRefundDate()!=null){
                                order.setRefundDate(DateFormatUtils.format(courseOrder.getRefundDate(),"yyyy-MM-dd HH:mm:ss"));
                            }
                            order.setOrgCode(cours.getOrgCode());
                            order.setSiteName(courseOrder.getSiteName());
                            if(StringUtils.isEmpty(courseOrder.getUserChannel())){
                                order.setChannel(courseOrder.getSiteName());
                            }else{
                                order.setChannel(APIConstant.API_DEFAULTVALUE);
                            }
                            if(StringUtils.isEmpty(courseOrder.getUserChannel())){
                                order.setChannelName(APIConstant.API_DEFAULTVALUE);
                            }else{
                                String channelName = xiaoYiCourseService.getChannelName(courseOrder.getUserChannel());
                                if(StringUtils.isEmpty(channelName)){
                                    order.setChannelName(APIConstant.API_DEFAULTVALUE);
                                }else{
                                    order.setChannelName(channelName);
                                }
                            }
                            order.setUserName(courseOrder.getUserName());
                            order.setUserMobile(courseOrder.getUserMobile());
                            if(courseOrder.getOrderFinishDate()!=null){
                                String format = DateFormatUtils.format(courseOrder.getOrderFinishDate(), "yyyy-MM-dd HH:mm:ss");
                                order.setOrderName(format+courseOrder.getRefundName()+"订单");
                            }else{
                                order.setOrderName(courseOrder.getRefundName()+"订单");
                            }
                            order.setClassType("直播课");
                            order.setOrderNum(courseOrder.getOrderNum());
                            order.setOrderType(courseOrder.getPayType());
                            order.setPayStatus(courseOrder.getPayStatus());
                            if(!StringUtils.isEmpty(courseOrder.getTotalCost())){
                                order.setCost(new BigDecimal(courseOrder.getTotalCost()));
                            }else{
                                order.setCost(new BigDecimal(0));
                            }
                            if(!StringUtils.isEmpty(courseOrder.getClassCost())){
                                order.setPrimeCost(new BigDecimal(courseOrder.getClassCost()));
                            }else{
                                order.setPrimeCost(new BigDecimal(0));
                            }
                            if(courseOrder.getOrderFinishDate()!=null){
                                order.setOrderFinishDate(courseOrder.getOrderFinishDate());
                            }else{
                                order.setOrderFinishDate(new Date());
                            }
                            Calendar calendar = Calendar.getInstance();
		                    calendar.set(Calendar.YEAR,0);
		                    calendar.set(Calendar.MONTH,0);
		                    calendar.set(Calendar.DATE,0);
		                    calendar.set(Calendar.HOUR_OF_DAY, 0);
		                    calendar.set(Calendar.MINUTE, 0);
		                    calendar.set(Calendar.SECOND, 0);
	 	                    calendar.set(Calendar.MILLISECOND, 0);
                            order.setOrderExpireDate(calendar.getTime());
                            //根据课程订单号码判断是否存在
                            List<XiaoYiOrder> xiaoYiOrders = xiaoYiOrderService.queryOrderByNum(courseOrder.getOrderNum());
                            if(xiaoYiOrders.size()>0){
                                for (XiaoYiOrder xiaoYiOrder : xiaoYiOrders) {
                                    log.info("正在修改["+cours.getCourseName()+"]课程订单信息");
                                    // 根据id修改
                                    order.setId(xiaoYiOrder.getId());
                                    order.setCreateDate(xiaoYiOrder.getCreateDate());
                                    xiaoYiOrderService.updateOrder(order);
                                    log.info("已修改["+cours.getCourseName()+"]课程订单信息");
                                }
                            }else{
                                log.info("正在添加["+cours.getCourseName()+"]课程订单信息");
                                order.setCreateDate(new Date());
                                xiaoYiOrderService.saveOrder(order);
                                log.info("已添加["+cours.getCourseName()+"]课程订单信息");
                            }
                        }
                    }else{
                        log.info("获取失败["+cours.getCourseName()+"]课程订单信息为空");
                    }
                //}
                TaskLogger taskLogger=new TaskLogger();
                taskLogger.setResult("成功");
                taskLogger.setTaskName("上传"+cours.getCourseName()+"课程订单信息");
                taskLogger.setCreatedAt(new Date());
                taskLogger.setUpdatedAt(new Date());
                taskLoggerService.saveTaskLogger(taskLogger);
            }
        /*}catch (Exception e){
            log.info("------------------------- 执行更新课程订单定时任务失败 -------------------------");
            log.info("异常信息:"+e.getMessage());
            TaskLogger taskLogger=new TaskLogger();
            taskLogger.setResult("失败");
            taskLogger.setError(e.getMessage());
            taskLogger.setTaskName(new Date()+"课程订单信息");
            taskLogger.setCreatedAt(new Date());
            taskLogger.setUpdatedAt(new Date());
            taskLoggerService.saveTaskLogger(taskLogger);
            return "失败"+e.getMessage();
        }*/
        long endTime = System.currentTimeMillis();
        String result = TimesTaskUtil.process(startTime, endTime);
        log.info("------------------------- 执行更新课程订单定时任务完毕 -------------------------");
        log.info(result);
        return result;
    }

    @Override
    public String pistonFlow() {
        log.info("------------------------- 执行开始、结束保利威推流任务开始 -------------------------");
        long startTime = System.currentTimeMillis();
        List<IntelligentRecordBean> recordBeans = intelligentRecordService.getIntelligentRecordBeans();
        for (IntelligentRecordBean recordBean : recordBeans) {
            String startDate = DateFormatUtils.format(recordBean.getStartDate(), "yyyy-MM-dd HH:mm:ss");
            String endDate = DateFormatUtils.format(recordBean.getEndDate(), "yyyy-MM-dd HH:mm:ss");
            log.info("正在检查编号为"+recordBean.getId()+"智能录播 ["+startDate+" - "+ endDate+"] 时间段");
            boolean flag = DateUtil.checkDate(new Date(), recordBean.getStartDate(), recordBean.getEndDate());
            if(flag){
                //推流状态为在开始状态
                if(recordBean.getFlowState() != 1){
                    //正在开始推流
                    StartPistonFlowResponse response = startPistonFlowAPI.process(new StartPistonFlowRequest(recordBean.getChannelId()));
                    if(!StringUtils.isEmpty(response) && !StringUtils.isEmpty(response.getCode()) && "200".equals(response.getCode())){
                        //开始推流成功
                        log.info("频道编号为"+recordBean.getChannelId()+"智能录播 ["+startDate+" - "+ endDate +"] 时间段数据已开始推流");
                        //修改推流状态
                        recordBean.setFlowState(1);
                        int flowState = intelligentRecordService.updateFlowState(recordBean);
                        String info = flowState > 0?"推流状态修改成功":"推流状态修改失败";
                        log.info("频道编号为"+recordBean.getChannelId()+"智能录播 ["+startDate+" - "+ endDate +"] 时间段数据"+info);
                    }else{
                        //开始推流失败
                        log.error("频道编号为"+recordBean.getChannelId()+"智能录播 ["+startDate+" - "+ endDate+"] 时间段数据开始推流失败!");
                    }
                }else{
                    log.info("频道编号为"+recordBean.getChannelId()+"智能录播 ["+startDate+" - "+ endDate +"] 时间段数据已开始推流(已跳过)");
                }
            }else{
                //推流状态为在结束状态
                if(recordBean.getFlowState() != 2){
                    //正在结束推流
                    StopPistonFlowResponse response = stopPistonFlowAPI.process(new StopPistonFlowRequest(recordBean.getChannelId()));
                    if(!StringUtils.isEmpty(response) && !StringUtils.isEmpty(response.getCode()) && "200".equals(response.getCode())){
                        //结束推流成功
                        log.info("频道编号为"+recordBean.getChannelId()+"智能录播 ["+startDate+" - "+ endDate +"] 时间段数据已结束推流");
                        //修改推流状态
                        recordBean.setFlowState(2);
                        int flowState = intelligentRecordService.updateFlowState(recordBean);
                        String info = flowState > 0?"推流状态修改成功":"推流状态修改失败";
                        log.info("频道编号为"+recordBean.getChannelId()+"智能录播 ["+startDate+" - "+ endDate +"] 时间段数据"+info);
                    }else{
                        //结束推流失败
                        log.error("频道编号为"+recordBean.getChannelId()+"智能录播 ["+startDate+" - "+ endDate+"] 时间段数据结束推流失败!");
                    }
                }else{
                    log.info("频道编号为"+recordBean.getChannelId()+"智能录播 ["+startDate+" - "+ endDate +"] 时间段数据已结束推流(已跳过)");
                }
            }
        }
        long endTime = System.currentTimeMillis();
        String result = TimesTaskUtil.process(startTime, endTime);
        log.info("------------------------- 执行开始、结束保利威推流任务完毕 -------------------------");
        log.info(result);
        return result;
    }

}
