package br.com.dbccompany.importadorarquivosbatch.cucumber.funcionalidade;

import br.com.dbccompany.importadorarquivosbatch.batch.ImportadorArquivosScheduler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ImportadorArquivosFuncionalidade {

    private final ImportadorArquivosScheduler importadorArquivosScheduler;

    public void executarImportacao() throws Exception {
        importadorArquivosScheduler.perform();
    }
}