package br.com.dbccompany.importadorarquivosbatch.repository.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.repository.GravadorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RepositorioException;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

import static java.text.MessageFormat.format;

@Repository
public class GravadorArquivoRepositoryFile implements GravadorArquivoRepository {

    private static final String SEPARADOR_NOME_EXTENSAO = "\\.";

    private static final String PADRAO_NOME_ARQUIVO_SAIDA = "{0}/{1}.done.{2}";
    private static final String PADRAO_DADOS_REGISTRO = "{0}ç{1}ç{2}ç{3}";

    private final Properties importacaoArquivosProperties;

    public GravadorArquivoRepositoryFile(Properties importacaoArquivosProperties) {
        this.importacaoArquivosProperties = importacaoArquivosProperties;
    }

    @Override
    public void gravar(DadosProcessamento dadosProcessamento) {
        try {
            final File arquivoSaida = new File(gerarNomeArquivoSaida(dadosProcessamento.getArquivoPath()));
            final FileWriter arquivoFileWriter = new FileWriter(arquivoSaida);
            arquivoFileWriter.write(gerar(dadosProcessamento));
            arquivoFileWriter.close();
        } catch (IOException excecao) {
            throw new RepositorioException(excecao);
        }
    }

    private String gerarNomeArquivoSaida(Path arquivo) {
        final String[] nomeIhExtensao = arquivo.getFileName().toString().split(SEPARADOR_NOME_EXTENSAO);
        return format(PADRAO_NOME_ARQUIVO_SAIDA, obterDiretorioSaida(), nomeIhExtensao[0], nomeIhExtensao[1]);
    }

    private String obterDiretorioSaida() {
        return importacaoArquivosProperties.getProperty("diretorioSaida");
    }

    private String gerar(DadosProcessamento dadosProcessamento) {
        return format(PADRAO_DADOS_REGISTRO, gerarParametros(dadosProcessamento));
    }

    private Object[] gerarParametros(DadosProcessamento dadosProcessamento) {
        return new Object[]{dadosProcessamento.getQuantidadeClientes(), dadosProcessamento.getQuantidadeVendedores(),
                dadosProcessamento.getIdVendaMaisCara(), dadosProcessamento.getNomePiorVendedor()};
    }
}