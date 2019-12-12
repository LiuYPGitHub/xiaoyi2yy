package com.ruoyi.web.controller.common.videoApi;
/**
 * 新建直播频道
 * @author CJ
 * @date 2019/12/3
 */
public interface AddLiveChannelAPI {

    AddLiveChannelResopnse process(AddLiveChannelRequest request);

    AddLiveChannelResopnse processJson(String json);

}
