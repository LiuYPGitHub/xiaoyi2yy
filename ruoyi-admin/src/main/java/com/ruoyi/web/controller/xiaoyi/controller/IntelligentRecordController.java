package com.ruoyi.web.controller.xiaoyi.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.web.controller.common.annotation.ShowLogger;
import com.ruoyi.web.controller.xiaoyi.domain.IntelligentRecord;
import com.ruoyi.web.controller.xiaoyi.domain.IntelligentRecordDetail;
import com.ruoyi.web.controller.xiaoyi.service.IntelligentRecordDetailService;
import com.ruoyi.web.controller.xiaoyi.service.IntelligentRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 智能录播精编版
 *
 * @author CJ
 * @date 2019/11/4
 */
@Controller
@Api(tags = {"添翼申学智能录播数据接口"})
public class IntelligentRecordController extends BaseController {

    @Autowired
    private IntelligentRecordService intelligentRecordService;
    @Autowired
    private IntelligentRecordDetailService intelligentRecordDetailService;

    @ShowLogger(info = "校翼-智能录播-精编版页面")
    @ApiOperation(value = "校翼-智能录播-精编版页面", notes = "校翼-智能录播-精编版页面")
    @RequestMapping(path = "/xiaoyi/intelligentRecord/index", method = RequestMethod.GET)
    public String index() {
        return "xiaoyi/intelligentRecord/index";
    }

    @ApiOperation(value = "校翼-智能录播-精编版列表", notes = "校翼-智能录播-精编版列表")
    @ShowLogger(info = "校翼-智能录播-精编版列表")
    @RequestMapping(path = "/xiaoyi/intelligentRecord/list", method = RequestMethod.POST)
    @ResponseBody
    public TableDataInfo classList(IntelligentRecord intelligentRecord) {
        startPage();
        intelligentRecord.setIsRevised(new Integer(2));
        List<IntelligentRecord> records = intelligentRecordService.selectByLike(intelligentRecord);
        intelligentRecordService.processData(records);
        return getDataTable(records);
    }

    @ShowLogger(info = "校翼-查询单个精编版录播课程")
    @ApiOperation(value = "校翼-查询单个精编版录播课程", notes = "校翼-查询单个精编版录播课程")
    @RequestMapping(path = "/xiaoyi/intelligentRecord/detailed/{id}", method = RequestMethod.GET)
    public String classById(@PathVariable(value = "id") Integer id, Model model) {
        IntelligentRecord record = intelligentRecordService.queryById(id);
        List<IntelligentRecordDetail> recordDetails = intelligentRecordDetailService.queryByCourseId(record.getId());
        model.addAttribute("record", record);
        model.addAttribute("recordDetail", recordDetails);
        return "xiaoyi/intelligentRecord/detailed";
    }

    @ShowLogger(info = "校翼-删除单个精编版录播课程")
    @ApiOperation(value = "校翼-删除单个精编版录播课程", notes = "校翼-删除单个精编版录播课程")
    @RequestMapping(path = "/xiaoyi/intelligentRecord/remove/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult classRem(@PathVariable(value = "id") Integer id) {
        int row = intelligentRecordService.dropIntelligentRecord(id);
        return AjaxResult.success(row > 0 ? true : false);
    }

}
