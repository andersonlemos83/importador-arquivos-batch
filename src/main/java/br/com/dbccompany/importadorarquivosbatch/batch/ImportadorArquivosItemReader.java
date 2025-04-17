package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.repository.MovedorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.service.LeitorArquivoService;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.ArquivoInvalidoException;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.InformacaoException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import static br.com.dbccompany.importadorarquivosbatch.shared.util.ObjectMapperUtil.generateJson;

@Log4j2
@Component
@AllArgsConstructor
public class ImportadorArquivosItemReader implements ItemReader<DadosLeitura> {

    private static final String SAINDO_DE_IMPORTADOR_ARQUIVOS_ITEM_READER = "Saindo de ImportadorArquivosItemReader: {}";

    private final LeitorArquivoService leitorArquivoService;
    private final MovedorArquivoRepository movedorArquivoRepository;

    @Override
    public DadosLeitura read() {
        try {
            log.info("Entrando em ImportadorArquivosItemReader");
            final DadosLeitura dadosLeitura = leitorArquivoService.lerArquivoNaoImportado();
            log.info(SAINDO_DE_IMPORTADOR_ARQUIVOS_ITEM_READER, generateJson(dadosLeitura));
            return dadosLeitura;
        } catch (InformacaoException excecao) {
            log.info(SAINDO_DE_IMPORTADOR_ARQUIVOS_ITEM_READER, excecao.getMessage());
            return null;
        } catch (ArquivoInvalidoException excecao) {
            log.info(SAINDO_DE_IMPORTADOR_ARQUIVOS_ITEM_READER, excecao.getMessage());
            movedorArquivoRepository.moverParaInvalido(excecao.getArquivoPath());
            return null;
        } catch (Exception excecao) {
            log.error("Ocorreu um erro durante a leitura do arquivo de entrada: {}", excecao.getMessage(), excecao);
            return null;
        }
    }
}