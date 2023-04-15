package com.cuidl.pip.core.controller.admin;

import com.cuidl.pip.common.result.Result;
import com.cuidl.pip.core.entity.IntegralGrade;
import com.cuidl.pip.core.service.IIntegralGradeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@Slf4j
@RequestMapping("admin/core/integralGrade")
public class AdminIntegralGradeController {

    @Resource
    private IIntegralGradeService integralGradeService;

    @GetMapping("list")
    @ApiOperation("积分等级列表")
    public Result getList() {
        List<IntegralGrade> list = integralGradeService.list();
        return Result.ok().data("list", list);
    }

    @ApiOperation(value = "根据id删除积分等级", notes="逻辑删除")
    @DeleteMapping("/remove/{id}")
    public Result removeById(
            @ApiParam(value = "数据id", required = true, example = "1")
            @PathVariable Long id){
        boolean result = integralGradeService.removeById(id);
        if(result){
            //return R.setResult(ResponseEnum.UPLOAD_ERROR);
            return Result.ok().message("删除成功");
        }else{
            return Result.error().message("删除失败");
        }
    }

    @ApiOperation("新增积分等级")
    @PostMapping("/save")
    public Result save(
            @ApiParam(value = "积分等级对象", required = true)
            @RequestBody IntegralGrade integralGrade){
        boolean result = integralGradeService.save(integralGrade);
        if (result) {
            return Result.ok().message("保存成功");
        } else {
            return Result.error().message("保存失败");
        }
    }

    @GetMapping("/get/{id}")
    public Result getById(
            @ApiParam(value = "数据id", required = true, example = "1")
            @PathVariable Long id
    ){
        IntegralGrade integralGrade = integralGradeService.getById(id);
        if(integralGrade != null){
            return Result.ok().data("record", integralGrade);
        }else{
            return Result.error().message("数据不存在");
        }
    }

    @PutMapping("/update")
    public Result updateById(
            @ApiParam(value = "积分等级对象", required = true)
            @RequestBody IntegralGrade integralGrade){
        boolean result = integralGradeService.updateById(integralGrade);
        if(result){
            return Result.ok().message("修改成功");
        }else{
            return Result.error().message("修改失败");
        }
    }

}

