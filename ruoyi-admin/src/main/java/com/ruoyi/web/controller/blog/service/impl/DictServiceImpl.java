package com.ruoyi.web.controller.blog.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.web.controller.blog.dao.DictMapper;
import com.ruoyi.web.controller.blog.domain.Dict;
import com.ruoyi.web.controller.blog.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 小平_字典
 */
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictMapper dictMapper;

    /**
     * 根据字典类型查询字典数据
     *
     * @param codeType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<Dict> selectDictDataByType(String codeType) {
        return dictMapper.selectDictDataByType(codeType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param codeType 字典类型
     * @param codeNo   字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String codeType, String codeNo) {
        return dictMapper.selectDictLabel1(codeType, codeNo);
    }

    /**
     * 根据条件分页查询字典数据
     *
     * @param dict 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<Dict> selectDictDataList(Dict dict)
    {
        return dictMapper.selectDictDataList(dict);
    }

    /**
     * 新增保存字典数据信息
     *
     * @param dict 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(Dict dict){
        return dictMapper.insertDictData(dict);
    }

    /**
     * 批量删除字典类型
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    @Override
    public int deleteDictTypeByIds(String ids){
        Long[] dictIds = Convert.toLongArray(ids);
        return dictMapper.deleteDictTypeByIds(dictIds);
    }

    /**
     * 根据字典类型ID查询信息
     *
     * @param codeId 字典类型ID
     * @return 字典类型
     */
    @Override
    public Dict selectDictTypeById(String codeId){
        return dictMapper.selectDictTypeById(codeId);
    }

    /**
     * 修改保存字典数据信息
     *
     * @param dict 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(Dict dict){
        return dictMapper.updateDictData(dict);
    }
}
