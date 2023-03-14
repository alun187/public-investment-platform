package com.cuidl.pip.core.service.impl;

import com.cuidl.pip.core.entity.UserLoginRecord;
import com.cuidl.pip.core.mapper.UserLoginRecordMapper;
import com.cuidl.pip.core.service.IUserLoginRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录记录表 服务实现类
 * </p>
 *
 * @author CuiDL
 * @since 2023-03-14
 */
@Service
public class UserLoginRecordServiceImpl extends ServiceImpl<UserLoginRecordMapper, UserLoginRecord> implements IUserLoginRecordService {

}
