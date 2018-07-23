package com.ikain.cloud.common.ribbon;

import com.ikain.cloud.common.bean.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by FengKai on 2018/7/23.
 */
@RestController
@RequestMapping(value = "role")
public class RoleServiceRibbon {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @RequestMapping(method = RequestMethod.POST)
    public void saveRole(@RequestBody RoleEntity role) {
        mongoTemplate.save(role);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<RoleEntity> findRoles() {
        Criteria criteria = new Criteria();
        Query query = new Query(criteria);
        return mongoTemplate.find(query, RoleEntity.class);
    }

}
