package com.ruoyi.web.controller.xiaoyi.service.impl;

import com.ruoyi.web.controller.xiaoyi.dao.LiveTeacherMapper;
import com.ruoyi.web.controller.xiaoyi.service.XiaoYiTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author CJ
 * @date 2019/10/24
 */
@Transactional
@Service
public class XiaoYiTeacherServiceImpl implements XiaoYiTeacherService {

    @Autowired
    private LiveTeacherMapper liveTeacherMapper;

    @Override
    public String queryIdByMobile(String mobile) {
        return liveTeacherMapper.selectIdByMobile(mobile);
    }

    @Override
    public int updateTeacherId(String teacherId, String id) {
        return liveTeacherMapper.updateTeacherIdById(teacherId,id);
    }

}
