package br.com.dbccompany.importadorarquivosbatch.domain;

import java.util.List;

public class DadosImportacao {

    private final List<String> dados;

    public DadosImportacao(List<String> dados) {

        this.dados = dados;
    }

    public List<String> getDados() {
        return dados;
    }
}