package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.service.ProcessadorArquivoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import static java.text.MessageFormat.format;

@Component
public class ImportadorArquivosItemProcessor implements ItemProcessor<DadosLeitura, DadosProcessamento> {

    private static final Logger LOG = LoggerFactory.getLogger(ImportadorArquivosItemProcessor.class);

    private final ProcessadorArquivoService processadorArquivoService;

    public ImportadorArquivosItemProcessor(ProcessadorArquivoService processadorArquivoService) {
        this.processadorArquivoService = processadorArquivoService;
    }

    @Override
    public DadosProcessamento process(DadosLeitura dadosLeitura) {
        try {
            return processadorArquivoService.processar(dadosLeitura);
        } catch (Exception excecao) {
            String mensagem = gerarMensagem(dadosLeitura);
            LOG.error(mensagem, excecao);
            excecao.printStackTrace();
            return null;
        }
    }

    private String gerarMensagem(DadosLeitura dadosLeitura) {
        return format("Ocorreu um erro durante o processamento do arquivo: {0}", dadosLeitura.toString());
    }
}