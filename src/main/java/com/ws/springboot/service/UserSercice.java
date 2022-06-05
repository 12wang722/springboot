package com.ws.springboot.service;

import com.ws.springboot.entity.User;
import com.ws.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSercice {

    @Autowired
    private UserMapper userMapper;


    public int save(User user){
        if(user.getId() == null) {   //user没有id，则表示新增
            return userMapper.insert(user);
        }else {                      //否则，表示更新
            return userMapper.update(user);
        }
    }

}
