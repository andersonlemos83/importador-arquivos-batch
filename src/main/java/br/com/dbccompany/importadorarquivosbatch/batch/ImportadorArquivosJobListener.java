package br.com.dbccompany.importadorarquivosbatch.batch;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.batch.core.BatchStatus.COMPLETED;
import static org.springframework.batch.core.BatchStatus.STARTED;

@Log4j2
@Component
public class ImportadorArquivosJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        Optional.ofNullable(jobExecution)
                .map(JobExecution::getStatus)
                .filter(STARTED::equals)
                .ifPresent(status -> log.info("---> Importador de Arquivos Iniciado."));
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        Optional.ofNullable(jobExecution)
                .map(JobExecution::getStatus)
                .filter(COMPLETED::equals)
                .ifPresent(status -> log.info("<--- Importador de Arquivos Encerrado."));
    }
}