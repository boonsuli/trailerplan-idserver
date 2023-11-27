package com.trailerplan.idserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages="com.trailerplan")
@EnableJpaRepositories
@EnableTransactionManagement
public class TrailerplanIdserverApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TrailerplanIdserverApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TrailerplanIdserverApplication.class);
	}
}
