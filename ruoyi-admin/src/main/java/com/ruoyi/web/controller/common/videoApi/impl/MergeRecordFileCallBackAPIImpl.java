package com.ruoyi.web.controller.common.videoApi.impl;

import com.ruoyi.web.controller.common.videoApi.MergeRecordFileCallBackAPI;
import com.ruoyi.web.controller.common.videoApi.MergeRecordFileCallBackRequest;
import com.ruoyi.web.controller.common.videoApi.MergeRecordFileCallBackResopnse;
import org.springframework.stereotype.Component;

/**
 * @author CJ
 * @date 2019/10/24
 */
@Component
public class MergeRecordFileCallBackAPIImpl implements MergeRecordFileCallBackAPI {
    @Override
    public MergeRecordFileCallBackResopnse process(MergeRecordFileCallBackRequest request) {
        MergeRecordFileCallBackResopnse resopnse=new MergeRecordFileCallBackResopnse();
        resopnse.setFileId(request.getFileId());
        resopnse.setFileIds(request.getFileIds());
        resopnse.setFileName(request.getFileName());
        resopnse.setFileUrl(request.getFileUrl());
        return resopnse;
    }
}
