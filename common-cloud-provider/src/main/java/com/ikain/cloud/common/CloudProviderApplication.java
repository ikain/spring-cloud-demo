package com.ikain.cloud.common;

import com.ikain.cloud.common.config.MongoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.ikain.cloud.common.feign", "com.ikain.cloud.common.ribbon"})
@Import({MongoConfig.class})
public class CloudProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudProviderApplication.class, args);
    }
}
