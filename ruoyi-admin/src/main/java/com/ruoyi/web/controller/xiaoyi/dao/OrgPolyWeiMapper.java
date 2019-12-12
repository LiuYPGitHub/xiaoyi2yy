package com.ruoyi.web.controller.xiaoyi.dao;

import com.ruoyi.web.controller.xiaoyi.bean.OrgPolyWeiBean;
import com.ruoyi.web.controller.xiaoyi.domain.OrgPolyWei;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author CJ
 * @date 2019/11/1
 */
public interface OrgPolyWeiMapper {

    List<OrgPolyWei> getOrgPolyWeiByOrgId(@Param(value = "orgId")String orgId);

    List<OrgPolyWei> selectList(OrgPolyWei polyWei);

    OrgPolyWei selectById(@Param(value = "id")Integer id);

    int updateById(
            @Param(value = "status")Integer status,
            @Param(value = "id")Integer id
    );

    int update(OrgPolyWei polyWei);

    List<OrgPolyWeiBean> queryAll();

    int insertOrgPolyWei(OrgPolyWei polyWei);

}
