package com.carrot.test.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Objects;

@RequiredArgsConstructor
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = LogDataSourceConfig.BASE_PACKAGE,
        entityManagerFactoryRef = LogDataSourceConfig.ENTITY_MANAGER_FACTORY,
        transactionManagerRef = LogDataSourceConfig.TRANSACTION_MANAGER
)
public class LogDataSourceConfig {

    public static final String BASE_PACKAGE = "com.carrot.test.domain.log";
    public static final String TRANSACTION_MANAGER = "logTransactionManager";
    public static final String ENTITY_MANAGER_FACTORY = "logEntityManagerFactory";
    public static final String PERSISTENCE_UNIT = "log";

    @ConfigurationProperties(prefix = "spring.dummy-datasource.hikari")
    @Bean("dummyDataSource")
    public DataSource dummyDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = TRANSACTION_MANAGER)
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactoryBean(builder).getObject()));
    }

    @Bean(name = ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(dummyDataSource())
                .packages(BASE_PACKAGE)
                .persistenceUnit(PERSISTENCE_UNIT)
                .build();
    }

}
