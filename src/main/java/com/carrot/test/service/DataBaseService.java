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
public class DataBaseService {

    private final AccountRepository accountRepository;
    private final TeamRepository teamRepository;
    private final DummyRepository dummyRepository;

    private final DataBaseService02 dataBaseService02;

    @Transactional
    public void saveAccount(){
        Team team = new Team();
        team.setName("FC서울6");
        Account account = new Account();
        account.setTeam(team);
        account.setGender("M6");
        account.setName("정광근6");

        teamRepository.save(team);
        accountRepository.save(account);

    }

    @Transactional(readOnly = true)
    public String getAccount(){
        Optional<Account> account = accountRepository.findByName("정광근2");
        return account.get().getGender();
    }

    @Transactional(transactionManager = LogDataSourceConfig.TRANSACTION_MANAGER)
    public void saveLog(){
        Dummy dummy = new Dummy();
        dummy.setContent("content");
        dummyRepository.save(dummy);
    }

    @Transactional(transactionManager = LogDataSourceConfig.TRANSACTION_MANAGER)
    public String getLog(){
        Optional<Dummy> content = dummyRepository.findByContent("content");
        return "hi";
    }

    @Transactional
    public void test01_outer()  {
        Team team = new Team();
        team.setName("맨유3");
        Account account = new Account();
        account.setTeam(team);
        account.setGender("F3");
        account.setName("홍길동3");

        log.info("currentTransactionName 01 : {}", TransactionSynchronizationManager.getCurrentTransactionName());
        teamRepository.save(team);
        accountRepository.save(account);

        dataBaseService02.db02_test01();

    }

    @Transactional
    public void test02_outer()  {
        Team team = new Team();
        team.setName("첼시2");
        Account account = new Account();
        account.setTeam(team);
        account.setGender("F2");
        account.setName("홍길동2");

        log.info("currentTransactionName 01 : {}", TransactionSynchronizationManager.getCurrentTransactionName());
        teamRepository.save(team);
        accountRepository.save(account);

        dataBaseService02.test02_inner();
        throw new RuntimeException("Exception for rollback");
    }


}
