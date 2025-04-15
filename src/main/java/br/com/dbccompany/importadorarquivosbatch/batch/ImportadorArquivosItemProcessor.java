package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.service.ProcessadorArquivoService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import static java.text.MessageFormat.format;

@Log4j2
@Component
@AllArgsConstructor
public class ImportadorArquivosItemProcessor implements ItemProcessor<DadosLeitura, DadosProcessamento> {

    private final ProcessadorArquivoService processadorArquivoService;

    @Override
    public DadosProcessamento process(DadosLeitura dadosLeitura) {
        try {
            final DadosProcessamento dadosProcessamento = processadorArquivoService.processar(dadosLeitura);
            log.info("Arquivo processado: " + dadosLeitura.getArquivoPath());
            return dadosProcessamento;
        } catch (Exception excecao) {
            String mensagem = gerarMensagem(dadosLeitura);
            log.error(mensagem, excecao);
            excecao.printStackTrace();
            return null;
        }
    }

    private String gerarMensagem(DadosLeitura dadosLeitura) {
        return format("Ocorreu um erro durante o processamento do arquivo: {0}", dadosLeitura.toString());
    }
}