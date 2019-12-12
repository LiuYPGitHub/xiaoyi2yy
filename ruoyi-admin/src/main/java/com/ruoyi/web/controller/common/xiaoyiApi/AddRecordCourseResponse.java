package com.ruoyi.web.controller.common.xiaoyiApi;

import com.ruoyi.web.controller.common.videoApi.BaseResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@ToString
@Getter
@Setter
public class AddRecordCourseResponse extends BaseResponse implements Serializable {

    private static final long serialVersionUID = -7627157592185997918L;

    public AddRecordCourseResponse(String classId) {
        success();
        this.classId = classId;
    }

    @ApiModelProperty(value ="课程编号",notes = "课程编号",required = true)
    private String classId;

}
