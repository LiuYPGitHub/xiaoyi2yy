package com.ruoyi.web.controller.xiaoyi.service.impl;

import com.ruoyi.web.controller.xiaoyi.bean.OrgPolyWeiBean;
import com.ruoyi.web.controller.xiaoyi.dao.OrgPolyWeiMapper;
import com.ruoyi.web.controller.xiaoyi.domain.OrgPolyWei;
import com.ruoyi.web.controller.xiaoyi.service.OrgPolyWeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author CJ
 * @date 2019/11/1
 */
@Transactional
@Service("orgPolyWei")
public class OrgPolyWeiServiceImpl implements OrgPolyWeiService {

    @Autowired
    private OrgPolyWeiMapper orgPolyWeiMapper;

    @Override
    public int deleteOrgPolyWeiById(Integer id) {
        OrgPolyWei orgPolyWei = orgPolyWeiMapper.selectById(id);
        orgPolyWei.setIsDel(2);
        return orgPolyWeiMapper.update(orgPolyWei);
    }

    @Override
    public List<OrgPolyWei> getOrgPolyWeiByOrgId(String orgId) {
        return orgPolyWeiMapper.getOrgPolyWeiByOrgId(orgId);
    }

    @Override
    public List<OrgPolyWei> queryOrgPolyWeiList(OrgPolyWei polyWei) {
        return orgPolyWeiMapper.selectList(polyWei);
    }

    @Override
    public OrgPolyWei queryById(Integer id) {
        return orgPolyWeiMapper.selectById(id);
    }

    @Override
    public int ubindId(Integer status,Integer id) {
        return orgPolyWeiMapper.updateById(status,id);
    }

    @Override
    public int bindId(OrgPolyWei polyWei) {
        return orgPolyWeiMapper.update(polyWei);
    }

    @Override
    public int saveOrgPolyWei(OrgPolyWei polyWei) {
        return orgPolyWeiMapper.insertOrgPolyWei(polyWei);
    }

    @Override
    public List<OrgPolyWeiBean> queryAll() {
        return orgPolyWeiMapper.queryAll();
    }

}
