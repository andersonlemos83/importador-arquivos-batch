package br.com.dbccompany.importadorarquivosbatch.repository;

import java.nio.file.Path;
import java.util.List;

public interface LeitorArquivoRepository {

    List<Path> lerArquivosNaoImportados();

}