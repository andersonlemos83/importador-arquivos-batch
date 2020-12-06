package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.DadosImportacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class ImportadorArquivosItemWriter implements ItemWriter<DadosImportacao> {

    private static final Logger LOG = LoggerFactory.getLogger(ImportadorArquivosItemWriter.class);

    @Override
    public void write(List<? extends DadosImportacao> dadosImportacoes) {
        List<String> dados = consolidarDados(dadosImportacoes);
        for (String dado : dados) {
            processarDados(dado);
        }
    }

    private List<String> consolidarDados(List<? extends DadosImportacao> dadosImportacoes) {
        List<String> xmls = new ArrayList<>();
        for (DadosImportacao dadosImportacao : dadosImportacoes) {
            xmls.addAll(dadosImportacao.getDados());
        }
        return xmls;
    }

    private void processarDados(String dado) {
        LOG.info("Escrevendo: " + dado);
    }
}