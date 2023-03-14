package com.cuidl.pip.core.service.impl;

import com.cuidl.pip.core.entity.Borrower;
import com.cuidl.pip.core.mapper.BorrowerMapper;
import com.cuidl.pip.core.service.IBorrowerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 借款人 服务实现类
 * </p>
 *
 * @author CuiDL
 * @since 2023-03-14
 */
@Service
public class BorrowerServiceImpl extends ServiceImpl<BorrowerMapper, Borrower> implements IBorrowerService {

}
