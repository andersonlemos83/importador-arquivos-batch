package br.com.dbccompany.importadorarquivosbatch.repository;

import br.com.dbccompany.importadorarquivosbatch.domain.Arquivo;

public interface LeitorArquivoRepository {

    Arquivo lerArquivoNaoImportado();

}