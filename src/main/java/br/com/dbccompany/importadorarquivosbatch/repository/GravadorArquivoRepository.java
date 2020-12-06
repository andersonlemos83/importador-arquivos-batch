package br.com.dbccompany.importadorarquivosbatch.repository;

import java.nio.file.Path;

public interface GravadorArquivoRepository {

    void gravar(Path arquivo);

}