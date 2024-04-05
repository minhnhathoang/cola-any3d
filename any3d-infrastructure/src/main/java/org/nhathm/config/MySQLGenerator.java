package org.nhathm.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Slf4j
@Configuration
public class MySQLGenerator {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    @Bean
    public void autoGenerate() {
        log.info("Start auto generate mybatis-plus code");

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> builder.author("nhathm")
                        .outputDir("D://mybatis-plus")
                        .commentDate("yyyy-MM-dd"))
                .strategyConfig(builder -> builder
                        .entityBuilder()
                        .enableLombok()
                        .naming(NamingStrategy.underline_to_camel)
                        .columnNaming(NamingStrategy.underline_to_camel)
                        .formatFileName("%sDO")
                        .controllerBuilder()
                        .formatFileName("%sController")
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        .serviceBuilder()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
