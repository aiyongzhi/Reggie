package com.ayz.reggie;

import com.ayz.reggie.DTO.DishDTO;
import com.ayz.reggie.utils.ValidateCodeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ReggieApplicationTests {
    @Autowired
    DataSource dataSource;

    @Autowired
            @Qualifier("customRedisTemplate")
    RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        //测试redis缓存，存储菜品分类下的所有菜品
        List<DishDTO> dishDTOList= (List<DishDTO>) redisTemplate.opsForValue().get("categoryId:1397844263642378242");
        for (DishDTO dishDTO : dishDTOList) {
            System.out.println(dishDTO);
        }
    }

}
