package br.com.dbccompany.importadorarquivosbatch.repository.impl;

import br.com.dbccompany.importadorarquivosbatch.repository.MovedorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RepositorioException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Properties;

import static br.com.dbccompany.importadorarquivosbatch.shared.util.ObjectMapperUtil.generateJson;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Log4j2
@Repository
@AllArgsConstructor
public class MovedorArquivoRepositoryFile implements MovedorArquivoRepository {

    private final Properties importacaoArquivosProperties;

    @Override
    public void moverParaInvalido(Path arquivo) {
        try {
            log.debug("Entrando em MovedorArquivoRepositoryFile: {}", generateJson(arquivo));
            final Path arquivoPathDestino = gerarArquivoPathDestino(arquivo);
            Files.move(arquivo, arquivoPathDestino, REPLACE_EXISTING);
            log.debug("Saindo de MovedorArquivoRepositoryFile");
        } catch (IOException excecao) {
            log.error("Ocorreu um erro durante movida do arquivo: {}", excecao.getMessage(), excecao);
            throw new RepositorioException(excecao);
        }
    }

    private Path gerarArquivoPathDestino(Path arquivoOrigem) {
        String caminhoDestino = gerarCaminhoDestino(arquivoOrigem);
        return Paths.get(caminhoDestino);
    }

    private String gerarCaminhoDestino(Path arquivoOrigem) {
        final String diretorioInvalido = importacaoArquivosProperties.getProperty("diretorioInvalido");
        return MessageFormat.format("{0}/{1}", diretorioInvalido, arquivoOrigem.getFileName().toString());
    }
}