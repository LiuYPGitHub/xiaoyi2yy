package com.ruoyi.web.controller.xiaoyi.service.impl;

import com.ruoyi.web.controller.xiaoyi.dao.TaskLoggerMapper;
import com.ruoyi.web.controller.xiaoyi.domain.TaskLogger;
import com.ruoyi.web.controller.xiaoyi.service.TaskLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author CJ
 * @date 2019/10/15
 */
@Transactional
@Service
public class TaskLoggerServiceImpl implements TaskLoggerService {

    @Autowired
    private TaskLoggerMapper taskLoggerMapper;

    @Override
    public int saveTaskLogger(TaskLogger taskLogger) {
        return taskLoggerMapper.insertTaskLogger(taskLogger);
    }

}
