package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.repository.ExcluidorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.repository.GravadorArquivoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.text.MessageFormat.format;

@Log4j2
@Component
@AllArgsConstructor
public class ImportadorArquivosItemWriter implements ItemWriter<DadosProcessamento> {

    private final GravadorArquivoRepository gravadorArquivoRepository;
    private final ExcluidorArquivoRepository excluidorArquivoRepository;

    @Override
    public void write(Chunk<? extends DadosProcessamento> dadosProcessamentoChuck) {
        try {
            dadosProcessamentoChuck.getItems().forEach(this::processar);
        } catch (Exception excecao) {
            String mensagem = gerarMensagem(dadosProcessamentoChuck.getItems());
            log.error(mensagem, excecao);
            excecao.printStackTrace();
        }
    }

    private void processar(DadosProcessamento dadosProcessamento) {
        gravadorArquivoRepository.gravar(dadosProcessamento);
        excluidorArquivoRepository.excluir(dadosProcessamento.getArquivoPath());
        log.info("Arquivo gravado: " + dadosProcessamento.getArquivoPath());
    }

    private String gerarMensagem(List<? extends DadosProcessamento> dadosProcessamentos) {
        return format("Ocorreu um erro durante a gravação dos arquivos de saída: {0}", dadosProcessamentos.toString());
    }
}