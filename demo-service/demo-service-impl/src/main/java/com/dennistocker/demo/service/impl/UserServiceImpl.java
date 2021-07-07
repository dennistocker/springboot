package com.dennistocker.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dennistocker.demo.dao.UserMapper;
import com.dennistocker.demo.model.User;
import com.dennistocker.demo.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @date 2021/5/24 4:45 下午
 */

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
