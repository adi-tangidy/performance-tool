package com.abb.es.df.perf.cmd;

import com.abb.es.df.perf.repo.TestRecordRepository;
import com.abb.es.df.perf.service.PostgreSQLService;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@Component
@CommandLine.Command(name="psqlCommand",
        mixinStandardHelpOptions = true, exitCodeOnExecutionException = 1)
public class PSQLCommand implements Callable<Integer> {

    private final PostgreSQLService psqlService;

    @CommandLine.Option(names = {"-c", "--count"}, description = "Test query load count", required = true)
    private int count;

    @CommandLine.Option(names = "--create", description = "Perform create test")
    private boolean isCreate;

    @CommandLine.Option(names = "--read", description = "Perform read test")
    private boolean isUpdate;

    @CommandLine.Option(names = "--update", description = "Perform update test")
    private boolean isRead;

    @CommandLine.Option(names = "--delete", description = "Perform delete test")
    private boolean isDelete;

    @CommandLine.Option(names = {"-o", "--output"}, description = "Summary file output")
    private String outFile;

    public PSQLCommand(PostgreSQLService psqlService) {
        this.psqlService = psqlService;
    }


    @Override
    public Integer call() throws Exception {

        if(isCreate) {
            long createdMs = psqlService.createRecord(count);
            System.out.println(String.format("%d Records created within %d ms", count, createdMs));
        }

        if(isUpdate) {
            long updatedMs = psqlService.updateRecord(count);
            System.out.println(String.format("%d Records updated within %d ms", count, updatedMs));
        }

        if(isRead) {
            long readMs = psqlService.readRecord(count);
            System.out.println(String.format("%d Records read within %d ms", count, readMs));
        }

        if(isDelete) {
            long deletedMs = psqlService.deleteRecord(count);
            System.out.println(String.format("%d Records deleted within %d ms", count, deletedMs));
        }

        return 0;
    }
}
