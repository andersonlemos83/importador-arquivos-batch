package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.service.ProcessadorArquivoService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import static br.com.dbccompany.importadorarquivosbatch.shared.util.ObjectMapperUtil.generateJson;

@Log4j2
@Component
@AllArgsConstructor
public class ImportadorArquivosItemProcessor implements ItemProcessor<DadosLeitura, DadosProcessamento> {

    private final ProcessadorArquivoService processadorArquivoService;

    @Override
    public DadosProcessamento process(DadosLeitura dadosLeitura) {
        try {
            log.info("Entrando em ImportadorArquivosItemProcessor: {}", generateJson(dadosLeitura));
            final DadosProcessamento dadosProcessamento = processadorArquivoService.processar(dadosLeitura);
            log.info("Saindo de ImportadorArquivosItemProcessor: {}", generateJson(dadosProcessamento));
            return dadosProcessamento;
        } catch (Exception excecao) {
            log.error("Ocorreu um erro durante o processamento do arquivo: {}", generateJson(dadosLeitura), excecao);
            return null;
        }
    }
}