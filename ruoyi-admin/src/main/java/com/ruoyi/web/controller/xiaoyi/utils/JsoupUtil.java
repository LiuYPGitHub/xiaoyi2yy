package com.ruoyi.web.controller.xiaoyi.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author CJ
 * @date 2019/10/22
 */
public class JsoupUtil {

    public static String replaceDesc(String text){
        Document document = Jsoup.parse(text);
        Elements img = document.select(".course-information-table td img");
        for (Element element : img) {
            String string = element.toString();
            //System.out.println("string:"+string);
            //string=string.replace("\" />","\">");
            text=text.replace(string,"☆");
        }
        return text;
    }

}
