package com.cuidl.pip.core.mapper;

import com.cuidl.pip.core.dto.ExcelDictDto;
import com.cuidl.pip.core.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author CuiDL
 * @since 2023-03-14
 */
public interface DictMapper extends BaseMapper<Dict> {

    void insertBatch(List<ExcelDictDto> list);

}
