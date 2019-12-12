package com.ruoyi.web.controller.common.xiaoyiApi;

/**
 * @author CJ
 * @date 2019/10/31
 */
public interface AddRecordLessonAPI {

    AddRecordLessonResponse process(AddRecordLessonRequest request);

    AddRecordLessonResponse processJson(String json);

}
