package dev.bondarmih.sddt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by bondarm on 05.08.16.
 */

@Configuration
@EnableJpaRepositories("dev.bondarmih.sddt.repository")
@EnableTransactionManagement
public class PersistenceConfig {

    @Autowired
    ServletContext context;

    @Bean
    public DataSource dataSource() {
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        String path = context.getRealPath("/")+ "/resources/db/";
        String jdbcUrl = "jdbc:sqlite:file:" + path + "sddt.sqlite";
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername("admin");
        dataSource.setPassword("supass");
        dataSource.setSuppressClose(true);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("dev.bondarmih.sddt.entity");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(additionalProperties());

        return entityManagerFactoryBean;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "com.enigmabridge.hibernate.dialect.SQLiteDialect");
        properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        return properties;
    }

    @Bean
    PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
