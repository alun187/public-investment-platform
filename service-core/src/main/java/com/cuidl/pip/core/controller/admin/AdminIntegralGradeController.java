package com.cuidl.pip.core.controller.admin;


import com.cuidl.pip.core.entity.IntegralGrade;
import com.cuidl.pip.core.service.IIntegralGradeService;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author CuiDL
 * @since 2023-03-14
 */
@RestController
@CrossOrigin
@RequestMapping("admin/core//integralGrade")
public class AdminIntegralGradeController {

    @Resource
    private IIntegralGradeService integralGradeService;

    @GetMapping("list")
    public List<IntegralGrade> getList() {
        return integralGradeService.list();
    }

}

