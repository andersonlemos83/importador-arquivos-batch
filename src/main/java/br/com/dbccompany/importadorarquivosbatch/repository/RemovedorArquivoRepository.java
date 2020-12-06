package br.com.dbccompany.importadorarquivosbatch.repository;

import java.nio.file.Path;

public interface RemovedorArquivoRepository {

    void remover(Path arquivo);

}