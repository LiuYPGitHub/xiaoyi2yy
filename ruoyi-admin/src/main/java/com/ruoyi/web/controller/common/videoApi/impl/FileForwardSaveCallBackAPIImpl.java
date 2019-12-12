package com.ruoyi.web.controller.common.videoApi.impl;

import com.ruoyi.web.controller.common.videoApi.FileForwardSaveCallBackAPI;
import com.ruoyi.web.controller.common.videoApi.FileForwardSaveCallBackRequest;
import com.ruoyi.web.controller.common.videoApi.FileForwardSaveCallBackResopnse;
import org.springframework.stereotype.Component;

/**
 * @author CJ
 * @date 2019/10/24
 */
@Component
public class FileForwardSaveCallBackAPIImpl implements FileForwardSaveCallBackAPI {
    @Override
    public FileForwardSaveCallBackResopnse process(FileForwardSaveCallBackRequest request) {
        FileForwardSaveCallBackResopnse resopnse=new FileForwardSaveCallBackResopnse();
        resopnse.setStatus(request.getStatus());
        return resopnse;
    }
}
