package br.com.dbccompany.importadorarquivosbatch.config;

import br.com.dbccompany.importadorarquivosbatch.batch.ImportadorArquivosJobFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.Scheduled;

import static java.lang.String.valueOf;
import static java.lang.System.currentTimeMillis;

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
@Import({BatchConfigurationAttachment.class})
public class BatchConfiguration {

    private final SimpleJobLauncher simpleJobLauncher;
    private final ImportadorArquivosJobFactory importadorArquivosJobFactory;

    public BatchConfiguration(SimpleJobLauncher simpleJobLauncher,
                              ImportadorArquivosJobFactory importadorArquivosJobFactory) {
        this.simpleJobLauncher = simpleJobLauncher;
        this.importadorArquivosJobFactory = importadorArquivosJobFactory;
    }

    //    @Scheduled(cron = "0 0/5 * * * ?", zone = "America/Maceio")
    @Scheduled(fixedDelay = 1000, zone = "America/Maceio")
    public void perform() throws Exception {
        simpleJobLauncher.run(importadorArquivosJobFactory.obterJob(), gerarJobParameters());
    }

    private JobParameters gerarJobParameters() {
        return new JobParametersBuilder().addString("JobID", valueOf(currentTimeMillis())).toJobParameters();
    }
}