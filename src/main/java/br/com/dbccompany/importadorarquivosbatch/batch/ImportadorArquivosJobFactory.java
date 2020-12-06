package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.DadosImportacao;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.stereotype.Component;

@Component
public class ImportadorArquivosJobFactory {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ImportadorArquivosJobListener importadorArquivosJobListener;
    private final ImportadorArquivosItemReader importadorArquivosItemReader;
    private final ImportadorArquivosItemWriter importadorArquivosItemWriter;

    public ImportadorArquivosJobFactory(JobBuilderFactory jobBuilderFactory,
                                        StepBuilderFactory stepBuilderFactory,
                                        ImportadorArquivosJobListener importadorArquivosJobListener,
                                        ImportadorArquivosItemReader importadorArquivosItemReader,
                                        ImportadorArquivosItemWriter importadorArquivosItemWriter) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.importadorArquivosJobListener = importadorArquivosJobListener;
        this.importadorArquivosItemReader = importadorArquivosItemReader;
        this.importadorArquivosItemWriter = importadorArquivosItemWriter;
    }

    public Job obterJob() {
        return jobBuilderFactory.get("importadorArquivosJob")
                .incrementer(new RunIdIncrementer())
                .listener(importadorArquivosJobListener)
                .start(gerarImportadorArquivosSteps())
                .build();
    }

    private Step gerarImportadorArquivosSteps() {
        return stepBuilderFactory.get("importadorArquivosSteps")
                .<DadosImportacao, DadosImportacao>chunk(1)
                .reader(importadorArquivosItemReader)
                .writer(importadorArquivosItemWriter)
                .build();
    }
}