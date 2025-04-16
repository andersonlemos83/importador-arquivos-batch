package br.com.dbccompany.importadorarquivosbatch.repository.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.repository.GravadorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RepositorioException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static br.com.dbccompany.importadorarquivosbatch.shared.util.ObjectMapperUtil.generateJson;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.text.MessageFormat.format;

@Log4j2
@Repository
@AllArgsConstructor
public class GravadorArquivoRepositoryFile implements GravadorArquivoRepository {

    private static final String SEPARADOR_NOME_EXTENSAO = "\\.";

    private static final String PADRAO_NOME_ARQUIVO_SAIDA = "{0}/{1}.done.{2}";
    private static final String PADRAO_DADOS_SAIDA = "{0}ç{1}ç{2}ç{3}";

    private final Properties importacaoArquivosProperties;

    @Override
    public void gravar(DadosProcessamento dadosProcessamento) {
        try {
            log.debug("Entrando em GravadorArquivoRepositoryFile: {}", generateJson(dadosProcessamento));
            final String nomeArquivoSaida = gerarNomeArquivoSaida(dadosProcessamento.getArquivoPath());
            final byte[] conteudo = gerarConteudo(dadosProcessamento);
            Files.write(Paths.get(nomeArquivoSaida), conteudo, CREATE, TRUNCATE_EXISTING);
            log.debug("Saindo de GravadorArquivoRepositoryFile");
        } catch (IOException excecao) {
            log.error("Ocorreu um erro durante gravação do arquivo: {}", excecao.getMessage(), excecao);
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

    private byte[] gerarConteudo(DadosProcessamento dadosProcessamento) {
        return format(PADRAO_DADOS_SAIDA, gerarParametros(dadosProcessamento)).getBytes();
    }

    private Object[] gerarParametros(DadosProcessamento dadosProcessamento) {
        return new Object[]{dadosProcessamento.getQuantidadeClientes(), dadosProcessamento.getQuantidadeVendedores(),
                dadosProcessamento.getIdVendaMaisCara(), dadosProcessamento.getNomePiorVendedor()};
    }
}