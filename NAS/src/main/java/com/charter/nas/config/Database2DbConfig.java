package com.charter.nas.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "Database2EntityManagerFactory",
    transactionManagerRef = "Database2TransactionManager", basePackages = {"com.charter.nas.bd2.models.dao"})
public class Database2DbConfig {

  @Bean(name = "Database2DataSource")
  @ConfigurationProperties(prefix = "database2.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  
  @Bean(name = "Database2EntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean barEntityManagerFactory(
      EntityManagerFactoryBuilder builder, @Qualifier("Database2DataSource") DataSource dataSource) {
    return builder.dataSource(dataSource).packages("com.charter.nas.bd2.models.entity").persistenceUnit("Table2_db2").build();
  }

  @Bean(name = "Database2TransactionManager")
  public PlatformTransactionManager barTransactionManager(
      @Qualifier("Database2EntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
    return new JpaTransactionManager(barEntityManagerFactory);
  }

}