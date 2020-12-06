package br.com.dbccompany.importadorarquivosbatch.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class ImportadorArquivosJobListener extends JobExecutionListenerSupport {

    private static final Logger LOG = LoggerFactory.getLogger(ImportadorArquivosJobListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.STARTED) {
            LOG.info("Importador de Arquivos Iniciado.");
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOG.info("Importador de Arquivos Encerrado.");
        }
    }
}