package com.ruoyi.web.controller.xiaoyi.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author CJ
 * @date 2019/10/24
 */
public interface LiveTeacherMapper {

    String selectIdByMobile(@Param(value = "mobile") String mobile);

    int updateTeacherIdById(
            @Param(value = "teacherId")String teacherId,
            @Param(value = "id")String id
    );

}
