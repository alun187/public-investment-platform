package com.cuidl.pip.core.service.impl;

import com.cuidl.pip.core.entity.UserAccount;
import com.cuidl.pip.core.mapper.UserAccountMapper;
import com.cuidl.pip.core.service.IUserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author CuiDL
 * @since 2023-03-14
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements IUserAccountService {

}
