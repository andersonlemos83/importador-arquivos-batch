package br.com.dbccompany.importadorarquivosbatch.cucumber.funcionalidade;

import br.com.dbccompany.importadorarquivosbatch.config.BatchConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImportadorArquivosFuncionalidade {

    @Autowired
    private BatchConfiguration batchConfiguration;

    public void executarImportacao() throws Exception {
        batchConfiguration.perform();
    }
}