package com.ruoyi.web.controller.common.xiaoyiApi.impl;

import com.ruoyi.web.controller.common.util.HttpClientUtil;
import com.ruoyi.web.controller.common.util.MD5Util;
import com.ruoyi.web.controller.common.util.URLUtil;
import com.ruoyi.web.controller.common.util.XiaoYiAPIJsonUtil;
import com.ruoyi.web.controller.common.xiaoyiApi.AddRecordLessonAPI;
import com.ruoyi.web.controller.common.xiaoyiApi.AddRecordLessonRequest;
import com.ruoyi.web.controller.common.xiaoyiApi.AddRecordLessonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author CJ
 * @date 2019/10/31
 */
@Slf4j
@Component
public class AddRecordLessonAPIImpl implements AddRecordLessonAPI {
    @Override
    public AddRecordLessonResponse process(AddRecordLessonRequest request) {
        String functionCode="addRecordLesson";
        String teacherId=request.getTeacherId();
        String classId=request.getClassId();
        String title=request.getTitle();
        String code= UUID.randomUUID().toString();
        String vid=request.getVid();
        String recordHour=request.getRecordHour();
        String allowTaste=request.getAllowTaste();
        String platform="liveCourseConnect";
        String timestamp= DateFormatUtils.format(Calendar.getInstance().getTime(), "yyyyMMddHHmmss");
        ;
        Map<String,String> map=new HashMap<>();
        map.put("functionCode",functionCode);
        map.put("teacherId",teacherId);
        map.put("classId",classId);
        map.put("title",title);
        map.put("code",code);
        if(!StringUtils.isEmpty(request.getCoverImgLink())){
            map.put("coverImgLink",request.getCoverImgLink());
        }
        if(!StringUtils.isEmpty(request.getVideoDuration())){
            map.put("videoDuration",request.getVideoDuration());
        }
        map.put("vid",vid);
        map.put("recordHour",recordHour);
        map.put("allowTaste",allowTaste);
        map.put("platform",platform);
        map.put("timestamp",timestamp);
        map.put("key",MD5Util.Ksort(map));
        map.put("title", URLUtil.getURLEncoderString(title));
        String url="http://frp.o-learn.cn:51085/thirdparty/liveCourseMaintenance/addRecordLesson";
        String json = HttpClientUtil.doPost(url, map);
        log.info("添加录播课节结果:"+json);
        return processJson(json);
    }

    @Override
    public AddRecordLessonResponse processJson(String json) {
        String lessonId = XiaoYiAPIJsonUtil.getJsonResult(json, "recordVideoId");
        return new AddRecordLessonResponse(lessonId);
    }
}
