package com.edgardo.localiza.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories("com.edgardo.localiza.model.repository")
@PropertySource({"classpath:application.properties"})
public class DataConfig {
	@Resource
	private Environment env;
	
	@Bean
	public DataSource dataSource(){
		BoneCPDataSource ds = new BoneCPDataSource();
		ds.setDriverClass(env.getRequiredProperty("db.driver"));
		ds.setJdbcUrl(env.getRequiredProperty("db.url"));
		ds.setUser(env.getRequiredProperty("db.user"));
		ds.setPassword(env.getRequiredProperty("db.password"));
		return ds;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource ds, JpaVendorAdapter jpaVendorAdapter){
		LocalContainerEntityManagerFactoryBean  factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(ds);
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		factoryBean.setJpaProperties(additionalProperties());
		factoryBean.setPackagesToScan("com.edgardo.localiza.model.*");
		return factoryBean;
	}
	
	@Bean
	public EntityManager entityManager(EntityManagerFactory entityManagerFactory){
		return entityManagerFactory.createEntityManager();
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter(){
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(Boolean.parseBoolean("false"));
		jpaVendorAdapter.setGenerateDdl(Boolean.parseBoolean("false"));
		jpaVendorAdapter.setDatabase(Database.POSTGRESQL);
		return jpaVendorAdapter;
	}
	
	Properties additionalProperties(){
		return new Properties(){
			private static final long serialVersionUID = 1L;{
				setProperty("hibernate.hbm2ddl.auto", "validate");
				setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
			}
		};
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(){
		return new JpaTransactionManager();
	}
}
