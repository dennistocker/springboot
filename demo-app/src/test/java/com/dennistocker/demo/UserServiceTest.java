package com.dennistocker.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dennistocker.demo.model.User;
import com.dennistocker.demo.service.api.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @date 2021/6/25 5:17 下午
 */

@Slf4j
@SpringBootTest
public class UserServiceTest {

    @Resource
    UserService userService;

    @Resource
    ObjectMapper objectMapper;

    @Test
    public void getTest() {
        User user = userService.getById(10000113);
        log.info(String.valueOf(user));

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("user_id", "user_name", "mobile").eq("user_id", 10000113);
        user = userService.getOne(wrapper);
        log.info(String.valueOf(user));
    }

    @Test
    public void listTest() {
        List<User> users = userService.list();
        for (User u : users) {
            log.info(String.valueOf(u));
        }
    }

    @SneakyThrows
    @Test
    public void pageTest() {
        IPage<User> users = userService.page(new Page<>(1, 3));
        log.info(objectMapper.writeValueAsString(users));

        users = userService.page(new Page<>(2, 3));
        log.info(objectMapper.writeValueAsString(users));
    }

}
