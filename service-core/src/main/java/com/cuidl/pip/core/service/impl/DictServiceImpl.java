package com.cuidl.pip.core.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuidl.pip.core.dto.ExcelDictDto;
import com.cuidl.pip.core.entity.Dict;
import com.cuidl.pip.core.listener.ExcelDictListener;
import com.cuidl.pip.core.mapper.DictMapper;
import com.cuidl.pip.core.service.IDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
@Slf4j
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    @Resource
    private RedisTemplate redisTemplate;

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
        // 先在redis中查询
        try {
            List<Dict> dictList = (List<Dict>) redisTemplate.opsForValue().get("pip:core:dictList:" + parentId);
            if (dictList != null) {
                log.info("从redis中取值");
                return dictList;
            }
        } catch (Exception e) {
            log.error("redis服务器异常" + ExceptionUtils.getStackTrace(e));
        }
        // 从数据库取值
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        List<Dict> list = baseMapper.selectList(wrapper);
        list.forEach(dict -> {
            dict.setHasChildren(hasChildren(dict.getId()));
        });

        // 写入redis
        try {
            redisTemplate.opsForValue().set("pip:core:dictList:" + parentId, list);
            log.info("数据存入redis");
        } catch (Exception e) {
            log.error("redis服务器异常" + ExceptionUtils.getStackTrace(e));
        }
        return list;
    }

    private Boolean hasChildren(Long parentId) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        Integer count = baseMapper.selectCount(wrapper);
        return count > 0;
    }
}
