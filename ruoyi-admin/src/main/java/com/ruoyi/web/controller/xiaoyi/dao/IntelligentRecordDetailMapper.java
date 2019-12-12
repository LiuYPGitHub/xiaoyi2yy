package com.ruoyi.web.controller.xiaoyi.dao;

import com.ruoyi.web.controller.xiaoyi.domain.IntelligentRecordDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author CJ
 * @date 2019/11/4
 */
public interface IntelligentRecordDetailMapper {

    List<IntelligentRecordDetail> selectById(@Param(value = "courseId") Integer courseId);

}
