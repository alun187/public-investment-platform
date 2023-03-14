package com.cuidl.pip.core.service.impl;

import com.cuidl.pip.core.entity.UserInfo;
import com.cuidl.pip.core.mapper.UserInfoMapper;
import com.cuidl.pip.core.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author CuiDL
 * @since 2023-03-14
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
