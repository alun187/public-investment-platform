package com.cuidl.pip.core.service.impl;

import com.cuidl.pip.core.entity.LendItem;
import com.cuidl.pip.core.mapper.LendItemMapper;
import com.cuidl.pip.core.service.ILendItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标的出借记录表 服务实现类
 * </p>
 *
 * @author CuiDL
 * @since 2023-03-14
 */
@Service
public class LendItemServiceImpl extends ServiceImpl<LendItemMapper, LendItem> implements ILendItemService {

}
