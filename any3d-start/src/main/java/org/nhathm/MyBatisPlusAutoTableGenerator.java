package org.nhathm;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;


/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public class MyBatisPlusAutoTableGenerator {

    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://localhost:3306/any3d", "admin", "password")
            .build();

    protected static StrategyConfig.Builder strategyConfig() {
        return new StrategyConfig.Builder();
    }

    protected static GlobalConfig.Builder globalConfig() {
        return new GlobalConfig.Builder();
    }

    protected static PackageConfig.Builder packageConfig() {
        return new PackageConfig.Builder();
    }

    protected static TemplateConfig.Builder templateConfig() {
        return new TemplateConfig.Builder();
    }

    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(strategyConfig()
                .addTablePrefix("t_")
                .addInclude("t_user")
                .build());
        generator.global(globalConfig()
                .outputDir(System.getProperty("user.dir") + "/any3d-infrastructure/src/main/resources/mybatis")
                .author("nhathm")

                .build()
        );
        generator.execute();
    }
}
