package com.carrot.test.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.SpringBeanContainer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Objects;

/**
 * 1. 아래의 설정 방법 참고 : https://gigas-blog.tistory.com/122  cf) 회사에서 사용하던 방식 : https://lemontia.tistory.com/967 (hibernate 설정이 들어가있음)
 * 2. persistence_unit 이란 : https://kwonnam.pe.kr/wiki/java/jpa/persistence.xml
 */
@RequiredArgsConstructor
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = BusinessDataSourceConfig.BASE_PACKAGE,
    entityManagerFactoryRef = BusinessDataSourceConfig.ENTITY_MANAGER_FACTORY,
    transactionManagerRef = BusinessDataSourceConfig.TRANSACTION_MANAGER
)
public class BusinessDataSourceConfig {

    public static final String BASE_PACKAGE = "com.carrot.test.domain.business";
    public static final String TRANSACTION_MANAGER = "businessTransactionManager";
    public static final String ENTITY_MANAGER_FACTORY = "businessEntityManagerFactory";
    public static final String PERSISTENCE_UNIT = "business";

    @Primary
    @Bean(name = TRANSACTION_MANAGER)
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder, @Qualifier("businessDataSource") DataSource businessDataSource) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactoryBean(builder, businessDataSource).getObject()));
    }

    @Primary
    @Bean(name = ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder, DataSource businessDataSource){
        return builder
                .dataSource(businessDataSource)
                .packages(BASE_PACKAGE)
                .persistenceUnit(PERSISTENCE_UNIT)
                .build();
    }

}
