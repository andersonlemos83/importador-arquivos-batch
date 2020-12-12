package br.com.dbccompany.importadorarquivosbatch.repository.impl;

import br.com.dbccompany.importadorarquivosbatch.repository.ExcluidorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RepositorioException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Repository
public class ExcluidorArquivoRepositoryFile implements ExcluidorArquivoRepository {

    @Override
    public void excluir(Path arquivo) {
        try {
            Files.delete(arquivo);
        } catch (IOException excecao) {
            throw new RepositorioException(excecao);
        }
    }
}