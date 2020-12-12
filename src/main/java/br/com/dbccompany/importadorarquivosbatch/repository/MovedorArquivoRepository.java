package br.com.dbccompany.importadorarquivosbatch.repository;

import java.nio.file.Path;

public interface MovedorArquivoRepository {

    void moverParaInvalido(Path arquivo);

}