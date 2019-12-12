package com.ruoyi.web.controller.xiaoyi.service;

import com.ruoyi.web.controller.xiaoyi.bean.OrgPolyWeiBean;
import com.ruoyi.web.controller.xiaoyi.domain.OrgPolyWei;
import java.util.List;

public interface OrgPolyWeiService {

    /**
     * 删除单个绑定机构
     * @param id 机构编号
     * @return
     */
    int deleteOrgPolyWeiById(Integer id);

    /**
     * 根据机构编号查询机构绑定信息
     * @param orgId 机构编号
     * @return
     */
    List<OrgPolyWei> getOrgPolyWeiByOrgId(String orgId);

    /**
     * 查询机构绑定保利威列表
     * @return
     */
    List<OrgPolyWei> queryOrgPolyWeiList(OrgPolyWei polyWei);

    /**
     * 根据主键查询单个机构绑定保利威数据
     * @param id
     * @return
     */
    OrgPolyWei queryById(Integer id);

    /**
     * @param status 用户解绑选择 1: 频道编号 2: 分类编号
     * @param id 主键
     * @return 影响的行数
     */
    int ubindId(Integer status,Integer id);

    /**
     * 内部机构绑定保利威
     */
    int bindId(OrgPolyWei polyWei);

    /**
     * 保存机构绑定数据
     * @param polyWei
     * @return
     */
    int saveOrgPolyWei(OrgPolyWei polyWei);
    /**
     * 查询全部机构
     * @return
     */
    List<OrgPolyWeiBean> queryAll();

}
