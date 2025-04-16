package br.com.dbccompany.importadorarquivosbatch.cucumber.stepdefs;

import br.com.dbccompany.importadorarquivosbatch.cucumber.contexto.ImportadorArquivosContexto;
import org.springframework.beans.factory.annotation.Autowired;

public class StepDefs {

    protected static String nomeArquivoSaida;

    @Autowired
    private ImportadorArquivosContexto importadorArquivosContexto;

    public void inicializarContexto() {
        importadorArquivosContexto.excluirDiretorios();
        importadorArquivosContexto.criarDiretorios();
    }

    public void finalizarContexto() {
        importadorArquivosContexto.excluirDiretorios();
    }
}