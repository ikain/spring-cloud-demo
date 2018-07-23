package com.ikain.cloud.common.ribbon;

import com.ikain.cloud.common.bean.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by FengKai on 2018/7/23.
 * REST + Ribbon 实现
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    private final String PROVIDER_NAME = "http://common-cloud-provider";

    private final String REQUEST_PREFIX = "/role";

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List getList() {
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(PROVIDER_NAME + REQUEST_PREFIX + "/list", List.class);
        return responseEntity.getBody();
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity saveRole(@RequestParam String name) {
        RoleEntity role = new RoleEntity();
        role.setName(name);
        return restTemplate.postForEntity(PROVIDER_NAME + REQUEST_PREFIX, role, void.class);
    }

}
