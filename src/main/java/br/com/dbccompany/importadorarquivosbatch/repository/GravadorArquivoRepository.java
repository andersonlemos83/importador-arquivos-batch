package br.com.dbccompany.importadorarquivosbatch.repository;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;

public interface GravadorArquivoRepository {

    void gravar(DadosProcessamento dadosProcessamento);

}