package com.ruoyi.web.controller.xiaoyi.dao;

import com.ruoyi.web.controller.xiaoyi.bean.IntelligentRecordBean;
import com.ruoyi.web.controller.xiaoyi.domain.IntelligentRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author CJ
 * @date 2019/11/4
 */
public interface IntelligentRecordMapper {

    /**
     * 修改智能录播开始、结束推流的状态
     * @param bean
     * @return
     */
    int updateFlowState(IntelligentRecordBean bean);
    /**
     * 获取智能录播频道、开始、结束时间不为空的数据
     * @return
     */
    List<IntelligentRecordBean> getIntelligentRecordBeans();

    List<IntelligentRecord> selectIntelligentRecordList(IntelligentRecord intelligentRecord);

    List<IntelligentRecord> selectIntelligentRecords(IntelligentRecord intelligentRecord);

    IntelligentRecord selectById(@Param(value = "id")Integer id);

    int deleteById(@Param(value = "id")Integer id);

    /**
     * 根据机构编号获取运营人名称
     * @param orgCode
     * @return
     */
    String getCreateName(@Param(value = "orgCode")String orgCode);

    /**
     * 根据机构编号获取机构名称
     * @param orgCode
     * @return
     */
    String getSiteName(@Param(value = "orgCode")String orgCode);

}
