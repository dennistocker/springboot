package com.dennistocker.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dennistocker.demo.common.annotation.ResultController;
import com.dennistocker.demo.model.User;
import com.dennistocker.demo.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @date 2021/5/24 3:30 下午
 */

@Slf4j
@ResultController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends BaseController {

    @Resource
    UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.list();
    }

    @GetMapping("/user")
    public User getUser(@RequestParam Integer id) {
        return userService.getById(id);
    }

    @GetMapping("/page")
    public Page<User> getUserByPage(@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
        return userService.page(new Page<>(pageNo, pageSize));
    }
}
