package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.DadosProcessamento;
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
    private final ImportadorArquivosItemProcessor importadorArquivosItemProcessor;
    private final ImportadorArquivosItemWriter importadorArquivosItemWriter;

    public ImportadorArquivosJobFactory(JobBuilderFactory jobBuilderFactory,
                                        StepBuilderFactory stepBuilderFactory,
                                        ImportadorArquivosJobListener importadorArquivosJobListener,
                                        ImportadorArquivosItemReader importadorArquivosItemReader,
                                        ImportadorArquivosItemProcessor importadorArquivosItemProcessor,
                                        ImportadorArquivosItemWriter importadorArquivosItemWriter) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.importadorArquivosJobListener = importadorArquivosJobListener;
        this.importadorArquivosItemReader = importadorArquivosItemReader;
        this.importadorArquivosItemProcessor = importadorArquivosItemProcessor;
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
                .<DadosLeitura, DadosProcessamento>chunk(1)
                .reader(importadorArquivosItemReader)
                .processor(importadorArquivosItemProcessor)
                .writer(importadorArquivosItemWriter)
                .build();
    }
}