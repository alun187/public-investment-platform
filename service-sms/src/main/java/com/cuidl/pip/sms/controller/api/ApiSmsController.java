package com.cuidl.pip.sms.controller.api;

import com.cuidl.pip.common.exception.Asserte;
import com.cuidl.pip.common.result.ResponseEnum;
import com.cuidl.pip.common.result.Result;
import com.cuidl.pip.common.utils.RandomUtils;
import com.cuidl.pip.common.utils.RegexValidateUtils;
import com.cuidl.pip.sms.service.ISmsService;
import com.cuidl.pip.sms.utiles.SmsProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author cuidl
 */
@RestController
@Api(tags = "短息服务")
@Slf4j
@CrossOrigin
@RequestMapping("/api/sms")
public class ApiSmsController {

    @Resource
    private ISmsService smsService;

    @Resource
    private RedisTemplate redisTemplate;

    @ApiOperation("获取验证码")
    @GetMapping("/send/{mobile}")
    public Result send(@ApiParam(value = "手机号", required = true) @PathVariable String mobile) {
        // 验证手机号
        Asserte.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        Asserte.isTrue(RegexValidateUtils.checkCellphone(mobile), ResponseEnum.MOBILE_ERROR);
        // 生成验证码
        HashMap<String, Object> map = new HashMap<>();
        String code = RandomUtils.getSixBitRandom();
        map.put("code", code);
        redisTemplate.opsForValue().set("pip:sms:code:" + mobile, code, 5, TimeUnit.MINUTES);
        smsService.send(mobile, SmsProperties.TEMPLATE_CODE, map);
        return Result.ok().data("code", code).message("验证码发送成功！");
    }
}
