package com.ruoyi.web.controller.xiaoyi.service.impl;


import com.ruoyi.web.controller.xiaoyi.dao.LiveCourseDetailMapper;
import com.ruoyi.web.controller.xiaoyi.domain.LiveCourseDetail;
import com.ruoyi.web.controller.xiaoyi.service.XiaoYiCourseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author CJ
 * @date 2019/10/14
 */
@Transactional
@Service
public class XiaoYiCourseDetailServiceImpl implements XiaoYiCourseDetailService {

    @Autowired
    private LiveCourseDetailMapper liveCourseDetailMapper;

    @Override
    public List<LiveCourseDetail> queryCourseById(Integer courseId) {
        return liveCourseDetailMapper.selectByCourseId(courseId);
    }

    @Override
    public int updateXiaoYiCourseDetail(LiveCourseDetail detail) {
        return liveCourseDetailMapper.updateXiaoYiCourseDetail(detail);
    }

}
