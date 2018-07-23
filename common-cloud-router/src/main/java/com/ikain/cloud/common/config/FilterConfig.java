package com.ikain.cloud.common.config;

import com.ikain.cloud.common.filer.PermissionFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by FengKai on 2018/7/23.
 */
@Configuration
public class FilterConfig {

    @Bean
    public PermissionFilter permissionFilter() {
        return new PermissionFilter();
    }
}
