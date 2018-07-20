package com.ikain.cloud.common.feign;

import com.ikain.cloud.common.bean.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by FengKai on 2018/6/19.
 */

@FeignClient(value = "common-cloud-provider")
public interface UserService {

    @GetMapping(value = "/user/list")
    List<UserEntity> findUsers(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "name", required = false) Integer sex);

    @PostMapping(value = "/user")
    void saveUser(@RequestBody UserEntity user);

    @PutMapping(value = "/user")
    void updateUser(@RequestBody UserEntity user);

    @GetMapping(value = "/user/{id}")
    UserEntity getUserById(@PathVariable(value = "id") String id);

    @DeleteMapping(value = "/user/{id}")
    void deleteUser(@PathVariable(value = "id") String id);
}
