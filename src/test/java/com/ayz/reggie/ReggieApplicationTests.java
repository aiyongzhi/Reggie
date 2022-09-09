package com.ayz.reggie;

import com.ayz.reggie.utils.ValidateCodeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ReggieApplicationTests {
    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() {
    }

}
