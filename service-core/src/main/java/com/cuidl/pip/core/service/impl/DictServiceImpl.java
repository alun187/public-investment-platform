package com.cuidl.pip.core.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuidl.pip.core.dto.ExcelDictDto;
import com.cuidl.pip.core.entity.Dict;
import com.cuidl.pip.core.listener.ExcelDictListener;
import com.cuidl.pip.core.mapper.DictMapper;
import com.cuidl.pip.core.service.IDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author CuiDL
 * @since 2023-03-14
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void excelUpload(InputStream inputStream) {
        EasyExcel.read(inputStream, ExcelDictDto.class, new ExcelDictListener(baseMapper)).sheet().doRead();
    }

    @Override
    public List<ExcelDictDto> listDictData() {
        List<Dict> dictList = baseMapper.selectList(null);
        ArrayList<ExcelDictDto> excelDictDtoList = new ArrayList<>(dictList.size());
        dictList.forEach(dict -> {
            ExcelDictDto excelDictDto = new ExcelDictDto();
            BeanUtils.copyProperties(dict, excelDictDto);
            excelDictDtoList.add(excelDictDto);
        });
        return excelDictDtoList;
    }

    @Override
    public List<Dict> getListByParentId(Long parentId) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        List<Dict> list = baseMapper.selectList(wrapper);
        list.forEach(dict -> {
            dict.setHasChildren(hasChildren(dict.getId()));
        });
        return list;
    }

    private Boolean hasChildren(Long parentId) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        Integer count = baseMapper.selectCount(wrapper);
        return count > 0;
    }
}
