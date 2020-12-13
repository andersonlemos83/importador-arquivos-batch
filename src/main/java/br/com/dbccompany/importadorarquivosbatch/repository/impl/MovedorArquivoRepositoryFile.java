package br.com.dbccompany.importadorarquivosbatch.repository.impl;

import br.com.dbccompany.importadorarquivosbatch.repository.MovedorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RepositorioException;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Properties;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Repository
public class MovedorArquivoRepositoryFile implements MovedorArquivoRepository {

    private final Properties importacaoArquivosProperties;

    public MovedorArquivoRepositoryFile(Properties importacaoArquivosProperties) {
        this.importacaoArquivosProperties = importacaoArquivosProperties;
    }

    @Override
    public void moverParaInvalido(Path arquivo) {
        try {
            final Path arquivoPathDestino = gerarArquivoPathDestino(arquivo);
            Files.move(arquivo, arquivoPathDestino, REPLACE_EXISTING);
        } catch (Exception excecao) {
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