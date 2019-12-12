package com.ruoyi.web.controller.blog.service;

import com.ruoyi.web.controller.blog.domain.Dict;

import java.util.List;

/**
 * 小平_字典
 */
public interface DictService {

    /**
     * 根据字典类型查询字典数据
     *
     * @param codeType 字典类型
     * @return 字典数据集合信息
     */
    List<Dict> selectDictDataByType(String codeType);

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param codeType 字典类型
     * @param codeNo   字典键值
     * @return 字典标签
     */
    String selectDictLabel(String codeType, String codeNo);

    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    List<Dict> selectDictDataList(Dict dictData);

    /**
     * 新增保存字典数据信息
     *
     * @param Dict 字典数据信息
     * @return 结果
     */
    int insertDictData(Dict Dict);

    /**
     * 批量删除字典类型
     *
     * @param ids 需要删除的数据
     * @return 结果
     * @throws Exception 异常
     */
    int deleteDictTypeByIds(String ids) throws Exception;

    /**
     * 根据字典类型ID查询信息
     *
     * @param codeId 字典类型ID
     * @return 字典类型
     */
    Dict selectDictTypeById(String codeId);

    /**
     * 修改保存字典数据信息
     *
     * @param dict 字典数据信息
     * @return 结果
     */
     int updateDictData(Dict dict);
}
