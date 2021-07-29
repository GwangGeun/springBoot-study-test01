package com.carrot.test.domain.log.repository;

import com.carrot.test.domain.log.entity.Dummy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DummyRepository extends JpaRepository<Dummy, Long> {
    Optional<Dummy> findByContent(String content);
}
