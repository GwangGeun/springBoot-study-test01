package com.carrot.test.service;

import com.carrot.test.config.datasource.ChainedTransactionManagerConfig;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Slf4j
@Service
@RequiredArgsConstructor
public class DatabaseService03 {

    private final AccountRepository accountRepository;
    private final TeamRepository teamRepository;
    private final DummyRepository dummyRepository;

    // business -> log  ::: log commit -> business commit
    @Transactional(value = ChainedTransactionManagerConfig.TRANSACTION_MANAGER, timeout=10)
    public void chainedTransactionManagerTest01(){
        business();
        log();
    }

    public void ex(){
        throw new RuntimeException();
    }


    @Transactional
    public void business(){

        log.info("currentTransactionName 01 : {}", TransactionSynchronizationManager.getCurrentTransactionName());

        Team team = new Team();
        team.setName("맨유6");
        Account account = new Account();
        account.setTeam(team);
        account.setGender("F6");
        account.setName("홍길동6");

        teamRepository.save(team);
        accountRepository.save(account);


    }

    @Transactional(transactionManager = LogDataSourceConfig.TRANSACTION_MANAGER)
    public void log(){

        log.info("currentTransactionName 02 : {}", TransactionSynchronizationManager.getCurrentTransactionName());

        Dummy dummy = new Dummy();
        dummy.setContent("내용 502");
        dummyRepository.save(dummy);

//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

    }

}
