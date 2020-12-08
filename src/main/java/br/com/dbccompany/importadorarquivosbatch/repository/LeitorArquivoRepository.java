package br.com.dbccompany.importadorarquivosbatch.repository;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;

public interface LeitorArquivoRepository {

    DadosLeitura lerArquivoNaoImportado();

}