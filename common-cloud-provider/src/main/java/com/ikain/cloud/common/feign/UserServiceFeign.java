package com.ikain.cloud.common.feign;

import com.ikain.cloud.common.bean.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by FengKai on 2018/6/19.
 * 用户管理业务控制
 */
@RestController
@RequestMapping(value = "/user")
public class UserServiceFeign implements UserService {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<UserEntity> findUsers(String name, Integer sex) {
        Criteria criteria = new Criteria();
        if (name != null && name.length() > 0)
            criteria.and("name").is(name);
        if (sex != null)
            criteria.and("sex").is(sex);
        Query query = new Query(criteria);
        return mongoTemplate.find(query, UserEntity.class);
    }

    public void saveUser(UserEntity user) {
        mongoTemplate.save(user);
    }

    public void updateUser(UserEntity user) {
        mongoTemplate.updateFirst(new Query(Criteria.where("id").is(user.getId())),
                Update.update("name", user.getName())
                        .set("sex", user.getSex())
                        .set("phone", user.getPhone())
                        .set("loginId", user.getLoginId())
                        .set("password", user.getPassword())
                        .set("email", user.getEmail()), UserEntity.class);
    }

    @Override
    public UserEntity getUserById(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), UserEntity.class);
    }

    @Override
    public void deleteUser(String id) {
        mongoTemplate.remove(new Query(Criteria.where("id").is(id)), UserEntity.class);
    }
}
