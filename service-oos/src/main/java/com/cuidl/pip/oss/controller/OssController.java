package com.cuidl.pip.oss.controller;

import com.cuidl.pip.common.exception.BusinessException;
import com.cuidl.pip.common.result.ResponseEnum;
import com.cuidl.pip.common.result.Result;
import com.cuidl.pip.oss.service.IOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author cuidl
 */
@Api(tags = "阿里云文件管理")
@RestController
@CrossOrigin
@RequestMapping("/api/oss/file")
public class OssController {
    @Resource
    private IOssService ossService;

    @PostMapping("upload")
    @ApiOperation("文件上传")
    public Result upload(
            @ApiParam(value = "文件", required = true)
            @RequestParam("file")MultipartFile file,
            @ApiParam(value = "模块", required = true)
            @RequestParam("module") String module) {
        try {
            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();
            String url = ossService.upload(inputStream, module, filename);
            return Result.ok().message("文件上传成功").data("url", url);
        } catch (IOException e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR, e);
        }
    }

    @ApiOperation("文件删除")
    @DeleteMapping("remove")
    public Result remove(
            @ApiParam(value = "文件url地址", required = true)
            @RequestParam("url") String url) {
        ossService.removeFile(url);
        return Result.ok().message("删除成功！");
    }
}
