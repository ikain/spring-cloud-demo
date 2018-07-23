package com.ikain.cloud.common.feign;

import com.alibaba.fastjson.JSON;
import com.ikain.cloud.common.bean.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserEntity> findUsers(@RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "sex", required = false) Integer sex) {
        return userService.findUsers(name, sex);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserEntity getUserById(@PathVariable(value = "id") String id) {
        return userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserEntity saveUser(@RequestParam String data) {
        UserEntity user = JSON.parseObject(data, UserEntity.class);
        userService.saveUser(user);
        return user;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UserEntity updateUser(@RequestParam String data){
        UserEntity user = JSON.parseObject(data, UserEntity.class);
        userService.updateUser(user);
        return user;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Map<String, String> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        return map;
    }

}
