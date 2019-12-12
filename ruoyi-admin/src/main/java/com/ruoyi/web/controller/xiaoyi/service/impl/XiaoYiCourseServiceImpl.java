package com.ruoyi.web.controller.xiaoyi.service.impl;

import com.ruoyi.web.controller.xiaoyi.dao.IntelligentRecordMapper;
import com.ruoyi.web.controller.xiaoyi.dao.LiveCourseMapper;
import com.ruoyi.web.controller.xiaoyi.domain.LiveCourse;
import com.ruoyi.web.controller.xiaoyi.service.XiaoYiCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Service
public class XiaoYiCourseServiceImpl implements XiaoYiCourseService {

    @Autowired
    private LiveCourseMapper liveCourseMapper;
    @Autowired
    private IntelligentRecordMapper intelligentRecordMapper;

    @Override
    public void processData(List<LiveCourse> courses) {
        for (LiveCourse record : courses) {
            String orgCode = record.getOrgCode();
            /*String createName = intelligentRecordMapper.getCreateName(orgCode);
            record.setCreateName(createName);*/
            String siteName = intelligentRecordMapper.getSiteName(orgCode);
            record.setSiteName(siteName);
        }
    }

    @Override
    public List<LiveCourse> selectByLike(LiveCourse course) {
        return liveCourseMapper.selectCourse(course);
    }

    @Override
    public List<Integer> getCourseIds() {
        return liveCourseMapper.getCourseIds();
    }

    @Override
    public LiveCourse queryById(Integer id) {
        return liveCourseMapper.selectById(id);
    }

    @Override
    public int updateCourse(LiveCourse course) {
        return liveCourseMapper.updateCourse(course);
    }

    @Override
    public String getSiteNameByPhone(String userId) {
        return liveCourseMapper.selectSiteNameByUserId(userId);
    }

    @Override
    public String getChannelName(String channel) {
        return liveCourseMapper.getChannelName(channel);
    }

}
