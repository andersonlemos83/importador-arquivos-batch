package br.com.dbccompany.importadorarquivosbatch.repository;

import java.nio.file.Path;

public interface ExcluidorArquivoRepository {

    void excluir(Path arquivo);

}