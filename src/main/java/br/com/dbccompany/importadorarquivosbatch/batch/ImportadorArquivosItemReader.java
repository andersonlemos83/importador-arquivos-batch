package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.DadosImportacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ImportadorArquivosItemReader implements ItemReader<DadosImportacao> {

    private static final Logger LOG = LoggerFactory.getLogger(ImportadorArquivosItemReader.class);

    @Override
    public DadosImportacao read() {
        try {
            List<String> dados = Arrays.asList("1", "2", "3");
            LOG.info("Lendo: " + dados.toString());
            return new DadosImportacao(dados);
        } catch (Exception excecao) {
            LOG.error("Ocorreu um erro durante a leitura dos arquivos", excecao);
            excecao.printStackTrace();
            return null;
        }
    }
}