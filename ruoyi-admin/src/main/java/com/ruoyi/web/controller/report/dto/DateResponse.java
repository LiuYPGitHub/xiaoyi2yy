package com.ruoyi.web.controller.report.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
@Setter
@Getter
public class DateResponse implements Serializable {

    private List<String> dateFormat;
    private List<String> sumCost;
    private List<String> sales14;
    private List<String> countSales14;

}
