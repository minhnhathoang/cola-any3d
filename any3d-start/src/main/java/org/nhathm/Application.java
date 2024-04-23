package org.nhathm;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.nhathm.config.MessageQueueProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * Spring Boot Starter
 */
@SpringBootApplication
@EnableWebSocket
@EnableTransactionManagement
@MapperScan(annotationClass = Mapper.class, basePackages = "org.nhathm")
@EnableConfigurationProperties(MessageQueueProperties.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
