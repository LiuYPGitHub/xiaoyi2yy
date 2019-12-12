package com.ruoyi.web.controller.blog.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.web.controller.blog.domain.Dict;
import com.ruoyi.web.controller.blog.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典
 */
@Controller
@RequestMapping("/blog/dict")
public class DictController extends BaseController {

    private String prefix = "blog/dict";

    @Autowired
    private DictService dictService;

    @GetMapping()
    public String dictData() {
        return prefix + "/dict";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Dict dict) {
        startPage();
        List<Dict> list = dictService.selectDictDataList(dict);
        return getDataTable(list);
    }

    /**
     * 新增字典类型
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存字典类型
     */
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated Dict dict) {
        dict.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(dictService.insertDictData(dict));
    }

    /**
     * 修改字典类型
     */
    @GetMapping("/edit/{codeId}")
    public String edit(@PathVariable("codeId") String codeId, ModelMap mmap) {
        mmap.put("dict", dictService.selectDictTypeById(codeId));
        return prefix + "/edit";
    }

    /**
     * 修改保存字典类型
     */
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated Dict dict) {
        dict.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(dictService.updateDictData(dict));
    }

    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(dictService.deleteDictTypeByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }
}
