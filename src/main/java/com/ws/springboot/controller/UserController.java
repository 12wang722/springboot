package com.ws.springboot.controller;

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
    private UserMapper userMapper;

    @Autowired
    private UserSercice userSercice;

    //新增和修改
    @PostMapping
    public Integer save(@RequestBody User user) {
        //新增或者更新
        return userSercice.save(user);
    }

    //显示所有数据
    @GetMapping
    public List<User> index(){

        return userMapper.findAll();
    }


    //删除
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id){
        return userMapper.deleteById(id);
    }

    //分页查询
    //接口路径：/user/page?pageNum=1&pageSize=10
    //@RequestParam接收
    //limit第一个参数 = （pageNum - 1）* pageSize
    //pageSize
    @GetMapping("/page")
    public Map<String , Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        List<User> data = userMapper.selectPage(pageNum,pageSize);
        Integer total = userMapper.selectTotal();
        Map<String , Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }

}
