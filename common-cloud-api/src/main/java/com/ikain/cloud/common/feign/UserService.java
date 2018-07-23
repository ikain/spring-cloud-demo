package com.ikain.cloud.common.feign;

import com.ikain.cloud.common.bean.UserEntity;
import com.ikain.cloud.common.config.FeignConfig;
import com.ikain.cloud.common.fallback.UserServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by FengKai on 2018/6/19.
 * Feign + Hystrix 实现
 */
@FeignClient(value = "common-cloud-provider/user", fallback = UserServiceFallBack.class, configuration = FeignConfig.class)
public interface UserService {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    List<UserEntity> findUsers(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "name", required = false) Integer sex);

    @RequestMapping(method = RequestMethod.POST)
    void saveUser(@RequestBody UserEntity user);

    @RequestMapping(method = RequestMethod.PUT)
    void updateUser(@RequestBody UserEntity user);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    UserEntity getUserById(@PathVariable(value = "id") String id);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void deleteUser(@PathVariable(value = "id") String id);
}
