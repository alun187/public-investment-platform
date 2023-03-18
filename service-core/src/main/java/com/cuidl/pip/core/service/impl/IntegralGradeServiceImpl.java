package com.cuidl.pip.core.service.impl;

import com.cuidl.pip.core.entity.IntegralGrade;
import com.cuidl.pip.core.mapper.IntegralGradeMapper;
import com.cuidl.pip.core.service.IIntegralGradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 积分等级表 服务实现类
 * </p>
 *
 * @author CuiDL
 * @since 2023-03-14
 */
@Service
@Slf4j
public class IntegralGradeServiceImpl extends ServiceImpl<IntegralGradeMapper, IntegralGrade> implements IIntegralGradeService {
}
