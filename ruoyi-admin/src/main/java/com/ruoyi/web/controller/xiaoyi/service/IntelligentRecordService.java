package com.ruoyi.web.controller.xiaoyi.service;

import com.ruoyi.web.controller.xiaoyi.bean.IntelligentRecordBean;
import com.ruoyi.web.controller.xiaoyi.domain.IntelligentRecord;

import java.util.List;

public interface IntelligentRecordService {

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

    /**
     * 处理智能录播显示的数据
     * @param records
     */
    void processData(List<IntelligentRecord> records);

    List<IntelligentRecord> selectByLike(IntelligentRecord intelligentRecord);

    IntelligentRecord queryById(Integer id);

    int dropIntelligentRecord(Integer id);

    /**
     * 根据机构编号获取运营人名称
     * @param orgCode
     * @return
     */
    String getCreateName(String orgCode);

    /**
     * 根据机构编号获取机构名称
     * @param orgCode
     * @return
     */
    String getSiteName(String orgCode);
}
