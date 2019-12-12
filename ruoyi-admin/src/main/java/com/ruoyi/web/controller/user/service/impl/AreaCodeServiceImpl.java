package com.ruoyi.web.controller.user.service.impl;

import com.ruoyi.web.controller.user.bean.AreaCodeBean;
import com.ruoyi.web.controller.user.dao.AreaCodeMapper;
import com.ruoyi.web.controller.user.service.AreaCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description：地区
 * @Author：kk
 * @Date：2019/9/27 16:42
 */
@Service
public class AreaCodeServiceImpl implements AreaCodeService {

    private @Autowired
    AreaCodeMapper areaCodeMapper;

    @Override
    public AreaCodeBean getArea(String codeId) {
        return areaCodeMapper.selectAreaCode(codeId);
    }
}
