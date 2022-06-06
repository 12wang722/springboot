package com.ws.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.springboot.entity.User;
import com.ws.springboot.mapper.UserMapper;
import com.ws.springboot.service.UserSercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserSercice userSercice;

    //新增和修改
    @PostMapping
    public boolean save(@RequestBody User user) {
        //新增或者更新
        return userSercice.saveUser(user);
    }

    //显示所有数据
    @GetMapping
    public List<User> index(){
        return userSercice.list();
    }


    //删除
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        return userSercice.removeById(id);
    }

    //分页查询
    //接口路径：/user/page?pageNum=1&pageSize=10
    //@RequestParam接收
    //limit第一个参数 = （pageNum - 1）* pageSize
    //pageSize
//    @GetMapping("/page")
//    public Map<String , Object> findPage(@RequestParam Integer pageNum,
//                                         @RequestParam Integer pageSize,
//                                         @RequestParam String username) {
//        pageNum = (pageNum - 1) * pageSize;
//        username= '%' + username + '%';
//        List<User> data = userMapper.selectPage(pageNum,pageSize,username);
//        Integer total = userMapper.selectTotal(username);
//        Map<String , Object> res = new HashMap<>();
//        res.put("data",data);
//        res.put("total",total);
//        return res;
//    }


    //分页查询 - mybatis-plus的方式
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String nickname,
                                @RequestParam(defaultValue = "") String address) {
        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!"".equals(username)) {
            queryWrapper.like("username", username);
        }
        if(!"".equals(nickname)) {
            queryWrapper.like("nickname", nickname);
        }
        if(!"".equals(address)) {
            queryWrapper.like("address", address);
        }
        return userSercice.page(page, queryWrapper);

    }
}
