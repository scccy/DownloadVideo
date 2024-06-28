package com.scccy.videoflowDy.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scccy.videoModel.domain.User;
import com.scccy.videoModel.mapper.UserMapper;
import com.scccy.videoflowDy.service.UserService;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}
