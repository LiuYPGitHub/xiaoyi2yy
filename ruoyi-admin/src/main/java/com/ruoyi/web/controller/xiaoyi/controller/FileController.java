package com.ruoyi.web.controller.xiaoyi.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.web.controller.common.annotation.ShowLogger;
import com.ruoyi.web.controller.xiaoyi.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author CJ
 * @date 2019/11/4
 */
@Controller
@Api(tags = {"校翼文件中心"})
public class FileController extends BaseController {

    @ApiOperation(value = "下载远程视频文件",notes = "下载远程视频文件")
    @ShowLogger(info = "下载远程视频文件")
    @RequestMapping(path = "/xiaoyi/file/",method = RequestMethod.GET)
    public void downloadVideo(@RequestParam String url,HttpServletResponse response) throws IOException {
        FileUtil.httpDownload(url,response);
    }

}
