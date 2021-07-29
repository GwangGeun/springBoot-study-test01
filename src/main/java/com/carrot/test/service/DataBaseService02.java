package com.carrot.test.service;

import com.carrot.test.config.datasource.LogDataSourceConfig;
import com.carrot.test.domain.business.entity.Account;
import com.carrot.test.domain.business.entity.Team;
import com.carrot.test.domain.business.repository.AccountRepository;
import com.carrot.test.domain.business.repository.TeamRepository;
import com.carrot.test.domain.log.entity.Dummy;
import com.carrot.test.domain.log.repository.DummyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataBaseService02 {

    private final AccountRepository accountRepository;
    private final TeamRepository teamRepository;
    private final DummyRepository dummyRepository;

    @Transactional(readOnly = true)
    public void db02_test01()  {
        Optional<Account> account = accountRepository.findByName("정광근2");
        log.info("currentTransactionName 02 : {}", TransactionSynchronizationManager.getCurrentTransactionName());
    }


    @Transactional(transactionManager = LogDataSourceConfig.TRANSACTION_MANAGER)
    public void test02_inner()  {
        log.info("currentTransactionName 02 : {}", TransactionSynchronizationManager.getCurrentTransactionName());
        Dummy dummy = new Dummy();
        dummy.setContent("내용 21");
        dummyRepository.save(dummy);

    }

}
