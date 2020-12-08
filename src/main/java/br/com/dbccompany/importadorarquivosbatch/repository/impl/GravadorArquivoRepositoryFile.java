package br.com.dbccompany.importadorarquivosbatch.repository.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.repository.GravadorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RepositorioException;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Properties;

@Repository
public class GravadorArquivoRepositoryFile implements GravadorArquivoRepository {

    private final Properties importacaoArquivosProperties;

    public GravadorArquivoRepositoryFile(Properties importacaoArquivosProperties) {
        this.importacaoArquivosProperties = importacaoArquivosProperties;
    }

    @Override
    public void gravar(DadosProcessamento dadosProcessamento) {
        try {
            File file = new File(gerarNomeArquivoSaida(dadosProcessamento.getArquivoPath()));
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(dadosProcessamento.toString());
            fileWriter.close();
        } catch (IOException excecao) {
            throw new RepositorioException(excecao);
        }
    }

    private String gerarNomeArquivoSaida(Path arquivo) {
        final String[] nomeIhExtensao = arquivo.getFileName().toString().split("\\.");
        return MessageFormat.format("{0}/{1}.done.{2}", obterDiretorioSaida(), nomeIhExtensao[0], nomeIhExtensao[1]);
    }

    private String obterDiretorioSaida() {
        return importacaoArquivosProperties.getProperty("diretorioSaida");
    }
}