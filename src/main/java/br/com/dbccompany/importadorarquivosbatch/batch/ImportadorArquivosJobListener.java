package br.com.dbccompany.importadorarquivosbatch.batch;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.batch.core.BatchStatus.COMPLETED;
import static org.springframework.batch.core.BatchStatus.STARTED;

@Log4j2
@Component
public class ImportadorArquivosJobListener extends JobExecutionListenerSupport {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == STARTED) {
            log.info("---> Importador de Arquivos Iniciado.");
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == COMPLETED) {
            log.info("<--- Importador de Arquivos Encerrado.");
        }
    }
}