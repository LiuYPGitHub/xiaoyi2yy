package com.ruoyi.web.controller.common.videoApi;

import java.security.NoSuchAlgorithmException;

/**
 * 新建视频分类
 * @author CJ
 * @date 2019/12/3
 */
public interface AddVideoCataAPI {

    AddVideoCataResopnse process(AddVideoCataRequest request) throws NoSuchAlgorithmException;

    AddVideoCataResopnse processJson(String json);

}
