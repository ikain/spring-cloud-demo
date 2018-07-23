package com.ikain.cloud.common.fallback;

import com.ikain.cloud.common.bean.UserEntity;
import com.ikain.cloud.common.feign.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceFallBack implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceFallBack.class);

    @Override
    public List<UserEntity> findUsers(String name, Integer sex) {
        LOGGER.info("findUsers(name[{}],age[{}]) request failed!", name, sex);
        return new ArrayList<>();
    }

    @Override
    public void saveUser(UserEntity user) {
        LOGGER.info("saveUser(UserEntity[{}]) request failed!", user);
    }

    @Override
    public void updateUser(UserEntity user) {
        LOGGER.info("updateUser(UserEntity[{}]) request failed!", user);
    }

    @Override
    public UserEntity getUserById(String id) {
        LOGGER.info("getUserById(id[{}]) request failed!", id);
        return new UserEntity();
    }

    @Override
    public void deleteUser(String id) {
        LOGGER.info("deleteUser(id[{}]) request failed!", id);
    }
}
