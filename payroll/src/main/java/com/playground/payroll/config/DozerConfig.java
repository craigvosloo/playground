package com.playground.payroll.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Configuration for bean mapping using dozer. We use dozer to simplify
 * and reduce code to transform JPA entities to DTO and back.
 * 
 * @author Craig Vosloo
 * @version %I%, %G%
 */
@Configuration
public class DozerConfig {

	@Bean
	public BeanMappingBuilder beanMappingBuilder() {
		BeanMappingBuilder builder = new BeanMappingBuilder() {
			@Override
			protected void configure() {
			}
		};

		return builder;
	}

	@Bean
	public Mapper mapper() {
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.addMapping(beanMappingBuilder());

		return mapper;
	}
}
