package com.dennistocker.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dennistocker.demo.dao.UserMapper;
import com.dennistocker.demo.model.User;
import com.dennistocker.demo.service.api.UserService;
import org.springframework.stereotype.Service;

/**
 * @date 2021/5/24 4:45 下午
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
