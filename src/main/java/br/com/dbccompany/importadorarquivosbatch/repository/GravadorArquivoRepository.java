package br.com.dbccompany.importadorarquivosbatch.repository;

import br.com.dbccompany.importadorarquivosbatch.domain.DadosProcessamento;

public interface GravadorArquivoRepository {

    void gravar(DadosProcessamento dadosProcessamento);

}