package org.nhathm.config;

import domain.mybatis.plugin.MybatisSqlLogInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@ConditionalOnBean(SqlSessionFactory.class)
@ConditionalOnProperty(name = MybatisPluginProperties.SQL_LOG_ENABLED)
@EnableConfigurationProperties({MybatisPluginProperties.class})
@RequiredArgsConstructor
@Slf4j
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Configuration(proxyBeanMethods = false)
public class MybatisPluginAutoConfiguration {

	public static final String AUTOWIRED_MYBATIS_SQL_LOG_INTERCEPTOR = "Autowired MybatisSqlLogInterceptor";

	private final MybatisPluginProperties mybatisPluginProperties;

	@Bean
	public MybatisSqlLogInterceptor mybatisSqlLogInterceptor() {
		log.debug(AUTOWIRED_MYBATIS_SQL_LOG_INTERCEPTOR);
		MybatisSqlLogInterceptor interceptor = new MybatisSqlLogInterceptor();
		interceptor.setSlownessThreshold(mybatisPluginProperties.getSqlLog().getSlownessThreshold());
		return interceptor;
	}
}
