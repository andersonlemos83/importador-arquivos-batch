package br.com.dbccompany.importadorarquivosbatch.repository.impl;

import br.com.dbccompany.importadorarquivosbatch.repository.ExcluidorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RepositorioException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static br.com.dbccompany.importadorarquivosbatch.shared.util.ObjectMapperUtil.generateJson;

@Log4j2
@Repository
public class ExcluidorArquivoRepositoryFile implements ExcluidorArquivoRepository {

    @Override
    public void excluir(Path arquivo) {
        try {
            log.debug("Entrando em ExcluidorArquivoRepositoryFile: {}", generateJson(arquivo));
            Files.delete(arquivo);
            log.debug("Saindo de ExcluidorArquivoRepositoryFile");
        } catch (IOException excecao) {
            log.error("Ocorreu um erro durante exclusão do arquivo: {}", excecao.getMessage(), excecao);
            throw new RepositorioException(excecao);
        }
    }
}