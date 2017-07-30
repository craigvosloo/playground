package com.playground.payroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = { PayrollApplication.class, Jsr310JpaConverters.class })
@EnableCaching
public class PayrollApplication {
	
	/**
	 * Used to initialize the localized error messages.
	 * 
	 * @return the ReloadableResourceBundleMessageSource	 * 
	 */
	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource messageSource() {
	  ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
	  messageBundle.setBasename("classpath:localization/messages");
	  messageBundle.setDefaultEncoding("UTF-8");
	  return messageBundle;
	}

	public static void main(String[] args) {
		SpringApplication.run(PayrollApplication.class, args);
	}
}
