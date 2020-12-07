package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.service.ConsolidadorService;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.InformacaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class ImportadorArquivosItemProcessor implements ItemProcessor<DadosLeitura, DadosProcessamento> {

    private static final Logger LOG = LoggerFactory.getLogger(ImportadorArquivosItemProcessor.class);

    private final ConsolidadorService consolidadorService;

    public ImportadorArquivosItemProcessor(ConsolidadorService consolidadorService) {
        this.consolidadorService = consolidadorService;
    }

    @Override
    public DadosProcessamento process(DadosLeitura dadosLeitura) {
        try {
            return consolidadorService.consolidar(dadosLeitura);
        } catch (InformacaoException excecao) {
            LOG.info(excecao.getMessage());
            return null;
        } catch (Exception excecao) {
            String mensagem = gerarMensagem(dadosLeitura);
            LOG.error(mensagem, excecao);
            excecao.printStackTrace();
            return null;
        }
    }

    private String gerarMensagem(DadosLeitura dadosLeitura) {
        return MessageFormat.format("Ocorreu um erro durante o processamento dos arquivos: {0}", dadosLeitura.toString());
    }
}