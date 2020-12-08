package br.com.dbccompany.importadorarquivosbatch.repository.impl;

import br.com.dbccompany.importadorarquivosbatch.repository.RemovedorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RepositorioException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Repository
public class RemovedorArquivoRepositoryFile implements RemovedorArquivoRepository {

    @Override
    public void remover(Path arquivo) {
        try {
            Files.deleteIfExists(arquivo);
        } catch (IOException excecao) {
            throw new RepositorioException(excecao);
        }
    }
}