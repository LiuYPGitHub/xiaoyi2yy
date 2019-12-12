package com.ruoyi.web.controller.report.dao;

import com.ruoyi.web.controller.report.domain.Cour;
import com.ruoyi.web.controller.report.domain.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Report form
 */

public interface ReportMapper {

    int coPending(@Param(value = "orgCode") String orgCode);

    int online(@Param(value = "orgCode") String orgCode);

    int notThrough(@Param(value = "orgCode") String orgCode);

    int sumSales(@Param(value = "orgCode") String orgCode);

    int ownChannelSales(@Param(value = "orgCode") String orgCode);

    int wings(@Param(value = "orgCode") String orgCode);

    List<Orders> students(@Param(value = "orgCode") String orgCode);

    List<Orders> ownChannel(@Param(value = "orgCode") String orgCode);

    List<Orders> wingsOwn(@Param(value = "orgCode") String orgCode);

    List<Cour> CourseName(@Param(value = "orgCode") String orgCode);

    List countCourseName(@Param(value = "orgCode") String orgCode);

    List<String> dateFormat();

    List<String> sales14(@Param("siteName") String siteName, @Param("orgCode") String orgCode);

    List<String> countSales14(@Param("siteName") String siteName,@Param("orgCode") String orgCode);

    List<String> institutions6();

    List<String> totalSales(String orgCode);

    List<String> totalSalesNo(String orgCode);

    List<String> totalSalesYes(String orgCode);

    List<String> selectSiteName(@Param(value = "orgCode") String orgCode);
}
