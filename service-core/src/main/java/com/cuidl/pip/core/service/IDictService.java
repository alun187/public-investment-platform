package com.cuidl.pip.core.service;

import com.cuidl.pip.core.dto.ExcelDictDto;
import com.cuidl.pip.core.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author CuiDL
 * @since 2023-03-14
 */
public interface IDictService extends IService<Dict> {
    void excelUpload(InputStream inputStream);

    List<ExcelDictDto> listDictData();

    List<Dict> getListByParentId(Long parentId);
}
