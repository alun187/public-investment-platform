package com.cuidl.pip.core.controller.admin;


import com.alibaba.excel.EasyExcel;
import com.cuidl.pip.common.exception.BusinessException;
import com.cuidl.pip.common.result.ResponseEnum;
import com.cuidl.pip.common.result.Result;
import com.cuidl.pip.core.dto.ExcelDictDto;
import com.cuidl.pip.core.entity.Dict;
import com.cuidl.pip.core.service.IDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author CuiDL
 * @since 2023-03-14
 */
@RestController
@RequestMapping("admin/core/dict")
@Api("数据字典管理")
@CrossOrigin
public class AdminDictController {

    @Autowired
    IDictService dictService;

    @PostMapping("import")
    @ApiOperation("excel批量导入数据字典")
    public Result excelUpload(
            @ApiParam(value = "Excel文件", required = true)
            @RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            dictService.excelUpload(inputStream);
            return Result.ok().message("文件上传成功");
        } catch (IOException e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR, e);
        }
    }

    @GetMapping("export")
    @ApiOperation("excel导出数据字典")
    public void export(HttpServletResponse response) {
        try {
            // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("mydict", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), ExcelDictDto.class).sheet("数据字典").doWrite(dictService.listDictData());

        } catch (IOException e) {
            //EXPORT_DATA_ERROR(104, "数据导出失败"),
            throw  new BusinessException(ResponseEnum.EXPORT_DATA_ERROR, e);
        }
    }

    @ApiOperation("通过上级id获取数据")
    @GetMapping("listByParentId/{parentId}")
    public Result getListByParentId(
            @ApiParam(value = "父ID", required = true)
            @PathVariable Long parentId) {
        List<Dict> list = dictService.getListByParentId(parentId);
        return Result.ok().data("list", list);
    }

}

