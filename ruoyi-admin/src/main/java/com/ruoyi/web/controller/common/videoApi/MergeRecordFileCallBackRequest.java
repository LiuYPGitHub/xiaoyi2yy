package com.ruoyi.web.controller.common.videoApi;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author CJ
 * @date 2019/10/24
 */
@ToString
@Getter
@Setter
public class MergeRecordFileCallBackRequest extends BaseCheck implements Serializable {

    private static final long serialVersionUID = -2224413968521626524L;

    @ApiModelProperty(value = "合并前的文件ID",notes = "合并前的文件ID")
    private String fileIds;
    @ApiModelProperty(value = "合并后的文件ID",notes = "合并后的文件ID")
    private String fileId;
    @ApiModelProperty(value = "合并后的m3u8的地址",notes = "合并后的m3u8的地址")
    private String fileUrl;
    @ApiModelProperty(value = "合并后的文件名称",notes = "合并后的文件名称")
    private String fileName;

}
