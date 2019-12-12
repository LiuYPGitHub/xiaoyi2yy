package com.ruoyi.web.controller.blog.service;

import com.ruoyi.web.controller.blog.domain.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 小平_字典 html调用 thymeleaf 实现字典读取
 */
@Service("Lz_dict")
public class DictBeanService {

    @Autowired
    private DictService dictService;

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param codeType 字典类型
     * @return 参数键值
     */
    public List<Dict> getType(String codeType) {
        return dictService.selectDictDataByType(codeType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param codeType 字典类型
     * @param codeNo   字典键值
     * @return 字典标签
     */
    public String getLabel(String codeType, String codeNo) {
        return dictService.selectDictLabel(codeType, codeNo);
    }

}
