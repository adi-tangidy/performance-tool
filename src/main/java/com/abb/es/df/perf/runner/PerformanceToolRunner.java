package com.abb.es.df.perf.runner;

import com.abb.es.df.perf.cmd.PSQLCommand;
import com.abb.es.df.perf.service.PostgreSQLService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

@Component
public class PerformanceToolRunner implements CommandLineRunner, ExitCodeGenerator {

    private final CommandLine.IFactory factory;
    private final PSQLCommand psqlCommand;
    private int exitCode;

    public PerformanceToolRunner(CommandLine.IFactory factory, PSQLCommand psqlCommand, PostgreSQLService psqlService) {
        this.factory = factory;
        this.psqlCommand = psqlCommand;
    }

    @Override
    public void run(String... args) throws Exception {
        exitCode = new CommandLine(psqlCommand, factory).execute(args);
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}
