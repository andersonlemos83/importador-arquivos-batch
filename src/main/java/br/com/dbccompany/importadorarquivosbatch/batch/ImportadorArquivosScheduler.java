package br.com.dbccompany.importadorarquivosbatch.batch;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
public class ImportadorArquivosScheduler {

    private final JobLauncher jobLauncher;
    private final Job importadorArquivosJob;

    @Scheduled(cron = "${importador-arquivos.cron}", zone = "America/Maceio")
    public void perform() throws Exception {
        log.info("Entrando em ImportadorArquivosScheduler");
        jobLauncher.run(importadorArquivosJob, gerarJobParameters());
        log.info("Saindo de ImportadorArquivosScheduler");
    }

    private JobParameters gerarJobParameters() {
        return new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
    }
}