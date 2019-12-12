package com.ruoyi.web.controller.common.xiaoyiApi;

/**
 * 添翼申学-添加直播课节接口
 * @author CJ
 * @date 2019/10/12
 */
public interface AddLiveLessonAPI {

    AddLiveLessonResponse process(AddLiveLessonRequest request) throws Exception;

    void checkParms(AddLiveLessonRequest request);

    String processJson(String json);

}
