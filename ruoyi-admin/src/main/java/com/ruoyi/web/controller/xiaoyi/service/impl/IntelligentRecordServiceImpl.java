package com.ruoyi.web.controller.xiaoyi.service.impl;

import com.ruoyi.web.controller.xiaoyi.bean.IntelligentRecordBean;
import com.ruoyi.web.controller.xiaoyi.dao.IntelligentRecordMapper;
import com.ruoyi.web.controller.xiaoyi.domain.IntelligentRecord;
import com.ruoyi.web.controller.xiaoyi.service.IntelligentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author CJ
 * @date 2019/11/4
 */
@Transactional
@Service
public class IntelligentRecordServiceImpl implements IntelligentRecordService {

    @Autowired
    private IntelligentRecordMapper intelligentRecordMapper;

    @Override
    public int updateFlowState(IntelligentRecordBean bean) {
        return intelligentRecordMapper.updateFlowState(bean);
    }

    @Override
    public List<IntelligentRecordBean> getIntelligentRecordBeans() {
        return intelligentRecordMapper.getIntelligentRecordBeans();
    }

    /**
     * 处理智能录播显示的数据
     * @param records
     */
    @Override
    public void processData(List<IntelligentRecord> records) {
        for (IntelligentRecord record : records) {
            String orgCode = record.getOrgCode();
            String createName = intelligentRecordMapper.getCreateName(orgCode);
            record.setCreateName(createName);
            String siteName = intelligentRecordMapper.getSiteName(orgCode);
            record.setSiteName(siteName);
        }
    }

    @Override
    public List<IntelligentRecord> selectByLike(IntelligentRecord intelligentRecord) {
        return intelligentRecordMapper.selectIntelligentRecordList(intelligentRecord);
    }

    @Override
    public IntelligentRecord queryById(Integer id) {
        return intelligentRecordMapper.selectById(id);
    }

    @Override
    public int dropIntelligentRecord(Integer id) {
        return intelligentRecordMapper.deleteById(id);
    }

    @Override
    public String getCreateName(String orgCode) {
        return intelligentRecordMapper.getCreateName(orgCode);
    }

    @Override
    public String getSiteName(String orgCode) {
        return intelligentRecordMapper.getSiteName(orgCode);
    }

}
