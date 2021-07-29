package com.carrot.test.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * ChainedTransactionManager 는 Datasource 상에서의 에러 발생 시에는 rollback 이 보장된다.
 * 다만, 후순위의 logic commit 시 에러가 났을 경우에만 문제가 발생 할 수 있다.
 * 현재는 Depreciated 되었기 때문에 추후 사용 목적보다는 이런게 있다는 것 정도를 알고 넘어가기 위한 테스트
 * ( JTA 를 사용하는 것을 권장 -> 2 phase commit )
 */
@Configuration
public class ChainedTransactionManagerConfig {

    public static final String TRANSACTION_MANAGER = "chainedTransactionManager";

    @Bean(name = ChainedTransactionManagerConfig.TRANSACTION_MANAGER)
    public PlatformTransactionManager chainedTransactionManager(
            @Qualifier(BusinessDataSourceConfig.TRANSACTION_MANAGER) PlatformTransactionManager firstTxManager
            , @Qualifier(LogDataSourceConfig.TRANSACTION_MANAGER) PlatformTransactionManager secondTxManager)
    {
        return new ChainedTransactionManager(firstTxManager, secondTxManager);
    }
}
