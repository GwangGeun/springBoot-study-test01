package com.carrot.test.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1. 설명 : LazyConnectionDataSourceProxy 를 이용한 read, write db 구분 설정
 * 2. 참고 : http://egloos.zum.com/kwon37xi/v/5364167
 *
 * 3. 테스트
 *      1-(1) outer(readWrite master) => REQUIRED && inner(readOnly slave) => NEW_REQUIRED 인 경우,
 *            inner 에서 readOnly 전용 dataSoruce 가 생기지 않고 outer 의 dataSource 에 의해 데이터를 읽어옴
 *
 *      1-(2) outer(readOnly slave) => REQUIRED && inner(readWrite master) => NEW_REQUIRED 인 경우,
 *            inner 에서 새로운 dataSource 가 부여되지 않고 outer 의 dataSource 를 사용한다. 때문에, write 에 실패하면서 에러 발생.
 */
@Slf4j
@Configuration
public class BusinessDataSourceRouteConfig {

    @Primary
    @DependsOn({"masterDataSource","slaveDataSource","routingDataSource"})
    @Bean("businessDataSource")
    public DataSource dataSource(){
        return new LazyConnectionDataSourceProxy(routingDataSource());
    }

    @Bean
    public DataSource routingDataSource(){
        ReplicationRoutingDataSource replicationRoutingDataSource = new ReplicationRoutingDataSource();

        Map<Object,Object> dataSourceMap = new ConcurrentHashMap<>();
        dataSourceMap.put(ReplicationRoutingDataSource.MASTER_DATASOURCE_KEY, masterDataSource());
        dataSourceMap.put(ReplicationRoutingDataSource.SLAVE_DATASOURCE_KEY, slaveDataSource());

        replicationRoutingDataSource.setTargetDataSources(dataSourceMap);
        replicationRoutingDataSource.setDefaultTargetDataSource(masterDataSource());

        return replicationRoutingDataSource;
    }

    @ConfigurationProperties(prefix = "spring.master-datasource.hikari")
    @Bean
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @ConfigurationProperties(prefix = "spring.slave-datasource.hikari")
    @Bean
    public DataSource slaveDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    public static class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

        private static final String MASTER_DATASOURCE_KEY = "master";
        private static final String SLAVE_DATASOURCE_KEY = "slave";

        @Override
        protected Object determineCurrentLookupKey() {

            boolean transactionActive = TransactionSynchronizationManager.isActualTransactionActive();
            if(transactionActive){
                System.out.println("TransactionSynchronizationManager.isCurrentTransactionReadOnly() = " + TransactionSynchronizationManager.isCurrentTransactionReadOnly());
                return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? SLAVE_DATASOURCE_KEY : MASTER_DATASOURCE_KEY;
            }
            return MASTER_DATASOURCE_KEY;
        }
    }

}
