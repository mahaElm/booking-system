package com.elm.grocery;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author oalamoud
 */
@Configuration
@EnableTransactionManagement
@Profile("!test")
@EnableJpaRepositories(entityManagerFactoryRef = "coreEntityManagerFactory",
		transactionManagerRef = "coreTransactionManager",
		basePackages = {"com.elm.grocery"})
public class JpaConfig {

	@Autowired
	private Environment env;

	@Autowired
	private DataSource dataSource;

	@Bean(name = "coreTransactionManager")
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(coreEntityManagerFactory().getObject());
		return transactionManager;
	}

	@Bean(name = "coreEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean coreEntityManagerFactory() {

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPersistenceUnitName("coreEntityManagerFactory");
		em.setPackagesToScan("com.elm.np.job.web.orm");
		em.setJpaVendorAdapter(hibernateJpaVendorAdapter());
		em.setJpaProperties(hibernateProperties());

		return em;
	}

	@Bean
	public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
		properties.put("hibernate.format_sql", env.getProperty("spring.jpa.format-sql"));
		properties.put("hibernate.id.new_generator_mappings", "false");
		properties.put("hibernate.default_schema", env.getProperty("spring.jpa.default_schema"));
		return properties;
	}

}
