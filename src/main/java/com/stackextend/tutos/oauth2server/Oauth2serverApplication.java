package com.stackextend.tutos.oauth2server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Oauth2serverApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Oauth2serverApplication.class)
                .run(
                "--spring.cloud.common.security.enabled=true",
                "--server.port=9999",
                "--logging.level.org.springframework=warn",
                "--spring.config.location=classpath:oauth2-server-config.yml"
                );
    }
}
