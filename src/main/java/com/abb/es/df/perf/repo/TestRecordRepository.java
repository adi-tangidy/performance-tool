package com.abb.es.df.perf.repo;

import com.abb.es.df.perf.model.TestRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRecordRepository extends JpaRepository<TestRecord, Long> {
    List<TestRecord> findAllByDescriptionEndingWith(String endWith);
}
