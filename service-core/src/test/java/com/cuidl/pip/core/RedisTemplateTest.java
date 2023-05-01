package com.cuidl.pip.core;


import com.cuidl.pip.core.entity.Dict;
import com.cuidl.pip.core.mapper.DictMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTemplateTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private DictMapper dictMapper;

    @Test
    public void saveDict() {
        Dict dict = dictMapper.selectById(1);
        redisTemplate.opsForValue().set("dict", dict, 5, TimeUnit.MINUTES);
    }

    @Test
    public void getDict() {
        Dict dict = (Dict)redisTemplate.opsForValue().get("dict");
        System.out.println("dict = " + dict);
    }
}
