package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.repository.ExcluidorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.repository.GravadorArquivoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import static br.com.dbccompany.importadorarquivosbatch.shared.util.ObjectMapperUtil.generateJson;

@Log4j2
@Component
@AllArgsConstructor
public class ImportadorArquivosItemWriter implements ItemWriter<DadosProcessamento> {

    private final GravadorArquivoRepository gravadorArquivoRepository;
    private final ExcluidorArquivoRepository excluidorArquivoRepository;

    @Override
    public void write(Chunk<? extends DadosProcessamento> dadosProcessamentoChuck) {
        try {
            log.info("Entrando em ImportadorArquivosItemWriter: {}", generateJson(dadosProcessamentoChuck));
            dadosProcessamentoChuck.getItems().forEach(this::processar);
            log.info("Saindo de ImportadorArquivosItemWriter");
        } catch (Exception excecao) {
            log.error("Ocorreu um erro durante a gravação dos arquivos de saída: {}", generateJson(dadosProcessamentoChuck.getItems()), excecao);
        }
    }

    private void processar(DadosProcessamento dadosProcessamento) {
        gravadorArquivoRepository.gravar(dadosProcessamento);
        excluidorArquivoRepository.excluir(dadosProcessamento.getArquivoPath());
        log.info("Arquivo gravado: {}", generateJson(dadosProcessamento));
    }
}