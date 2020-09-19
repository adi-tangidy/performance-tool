package com.abb.es.df.perf.service;

import com.abb.es.df.perf.model.TestRecord;
import com.abb.es.df.perf.repo.TestRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class PostgreSQLService {
    private static final Logger log = LoggerFactory.getLogger(PostgreSQLService.class);
    private final TestRecordRepository testRecordRepository;
    private String createUUID;

    public PostgreSQLService(TestRecordRepository testRecordRepository) {
        this.testRecordRepository = testRecordRepository;
    }

    /**
     * Create records and time the whole process
     *
     * @param count number of records to be created
     * @return time it took to complete the insert process (ms)
     */
    public long createRecord(int count){
        long startMillis = System.currentTimeMillis();
        log.debug("creating {} records, started {}", count, startMillis);
        create(count);
        long endMillis = System.currentTimeMillis();
        log.debug("creating {} records, finished {}", count, endMillis);
        return endMillis - startMillis;
    }

    public long updateRecord(int count) {
        createIfRequired(count);
        List<TestRecord> testRecord = testRecordRepository.findAllByDescriptionEndingWith(createUUID);
        long startMillis = System.currentTimeMillis();
        log.debug("updating {} records, started {}", count, startMillis);
        testRecord.forEach(record -> {
            record.setDescription(String.format("Update %s", record.getDescription()));
            testRecordRepository.save(record);
        });
        long endMillis = System.currentTimeMillis();
        log.debug("updating {} records, finished {}", count, endMillis);
        return endMillis - startMillis;
    }

    public long readRecord(int count) {
        createIfRequired(count);
        List<TestRecord> testRecord = testRecordRepository.findAllByDescriptionEndingWith(createUUID);
        long startMillis = System.currentTimeMillis();
        log.debug("reading {} records, started {}", count, startMillis);
        testRecord.forEach(record -> testRecordRepository.findById(record.getId()));
        long endMillis = System.currentTimeMillis();
        log.debug("reading {} records, finished {}", count, endMillis);
        return endMillis - startMillis;
    }

    public long deleteRecord(int count) {
        createIfRequired(count);
        List<TestRecord> testRecord = testRecordRepository.findAllByDescriptionEndingWith(createUUID);
        long startMillis = System.currentTimeMillis();
        log.debug("deleting {} records, started {}", count, startMillis);
        testRecord.forEach(record -> testRecordRepository.deleteById(record.getId()));
        long endMillis = System.currentTimeMillis();
        log.debug("deleting {} records, finished {}", count, endMillis);
        return endMillis - startMillis;
    }

    /**
     * Creating record
     *
     * @param count number of record to be created
     */
    private void create(int count) {
        createUUID = UUID.randomUUID().toString();
        for (int i = 0; i < count; i++) {
            testRecordRepository.save(TestRecord.builder()
                    .description(String.format("test record %s", createUUID))
                    .build());
        }
    }

    private void createIfRequired(int count){
        if(StringUtils.isEmpty(createUUID))
            create(count);
    }

}
