package com.ikain.cloud.common.feign;

import com.alibaba.fastjson.JSON;
import com.ikain.cloud.common.bean.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by FengKai on 2018/6/19.
 * 用户管理业务控制
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/list")
    public List<UserEntity> findUsers(@RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "sex", required = false) Integer sex) {
        return userService.findUsers(name, sex);
    }

    @GetMapping(value = "/{id}")
    public UserEntity getUserById(@PathVariable(value = "id") String id) {
        return userService.getUserById(id);
    }

    @PostMapping()
    public UserEntity saveUser(@RequestParam String data) {
        UserEntity user = JSON.parseObject(data, UserEntity.class);
        userService.saveUser(user);
        return user;
    }

    @PutMapping()
    public UserEntity updateUser(@RequestParam String data){
        UserEntity user = JSON.parseObject(data, UserEntity.class);
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping(value = "/{id}")
    public Map<String, String> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        return map;
    }

}
