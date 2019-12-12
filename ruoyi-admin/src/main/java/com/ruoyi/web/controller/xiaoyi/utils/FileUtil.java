package com.ruoyi.web.controller.xiaoyi.utils;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * @author CJ
 * @date 2019/11/4
 */
@Slf4j
public class FileUtil {
    public static boolean httpDownload(String httpUrl, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("video.mp4", "UTF-8"));
        // 1.下载网络文件
        int byteRead;
        URL url=null;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            //2.获取链接
            URLConnection conn = url.openConnection();
            //3.输入流
            InputStream inStream = conn.getInputStream();
            //3.写入文件
            OutputStream os = response.getOutputStream();

            byte[] buffer = new byte[1024];
            while ((byteRead = inStream.read(buffer)) != -1) {
                os.write(buffer, 0, byteRead);
            }
            inStream.close();
            os.close();
            return true;
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            log.error(e.getMessage());
            //e.printStackTrace();
            return false;
        }
    }
}
