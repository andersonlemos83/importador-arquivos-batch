package br.com.dbccompany.importadorarquivosbatch.config;

import br.com.dbccompany.importadorarquivosbatch.batch.ImportadorArquivosItemProcessor;
import br.com.dbccompany.importadorarquivosbatch.batch.ImportadorArquivosItemReader;
import br.com.dbccompany.importadorarquivosbatch.batch.ImportadorArquivosItemWriter;
import br.com.dbccompany.importadorarquivosbatch.batch.ImportadorArquivosJobListener;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfiguration {

    @Bean
    public Job importadorArquivosJob(JobRepository jobRepository,
                                     ImportadorArquivosJobListener importadorArquivosJobListener,
                                     Step importadorArquivosStep) {
        return new JobBuilder("importadorArquivosJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(importadorArquivosJobListener)
                .start(importadorArquivosStep)
                .build();
    }

    @Bean
    public Step importadorArquivosStep(JobRepository jobRepository,
                                       PlatformTransactionManager transactionManager,
                                       ImportadorArquivosItemReader importadorArquivosItemReader,
                                       ImportadorArquivosItemProcessor importadorArquivosItemProcessor,
                                       ImportadorArquivosItemWriter importadorArquivosItemWriter) {
        return new StepBuilder("importadorArquivosStep", jobRepository)
                .<DadosLeitura, DadosProcessamento>chunk(1, transactionManager)
                .reader(importadorArquivosItemReader)
                .processor(importadorArquivosItemProcessor)
                .writer(importadorArquivosItemWriter)
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}