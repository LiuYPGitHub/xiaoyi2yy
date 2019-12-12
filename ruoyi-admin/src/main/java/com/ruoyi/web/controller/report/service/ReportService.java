package com.ruoyi.web.controller.report.service;

import com.ruoyi.web.controller.report.dto.CourseIndexResponse;
import com.ruoyi.web.controller.report.dto.DateResponse;
import com.ruoyi.web.controller.report.dto.institutions6Response;

import java.util.List;

/**
 * Report form
 */

public interface ReportService {

    int coPending(String orgCode);

    int online(String orgCode);

    int notThrough(String orgCode);

    int sales(String orgCode);

    int ownChannelSales(String orgCode);

    int wings(String orgCode);

    int students(String orgCode);

    int ownChannel(String orgCode);

    int wingsOwn(String orgCode);

    CourseIndexResponse CourseName(String orgCode);

    DateResponse dateFormat(String siteName, String orgCode);

    institutions6Response institutions6(String orgCode);

    List<String> selectSiteName(String orgCode);
}
