package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.repository.MovedorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.service.LeitorArquivoService;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.ArquivoInvalidoException;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.InformacaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
public class ImportadorArquivosItemReader implements ItemReader<DadosLeitura> {

    private static final Logger LOG = LoggerFactory.getLogger(ImportadorArquivosItemReader.class);

    private final LeitorArquivoService leitorArquivoService;
    private final MovedorArquivoRepository movedorArquivoRepository;

    public ImportadorArquivosItemReader(LeitorArquivoService leitorArquivoService,
                                        MovedorArquivoRepository movedorArquivoRepository) {
        this.leitorArquivoService = leitorArquivoService;
        this.movedorArquivoRepository = movedorArquivoRepository;
    }

    @Override
    public DadosLeitura read() {
        try {
            final DadosLeitura dadosLeitura = leitorArquivoService.lerArquivoNaoImportado();
            LOG.info("Arquivo lido: " + dadosLeitura.getArquivoPath());
            return dadosLeitura;
        } catch (InformacaoException excecao) {
            LOG.info(excecao.getMessage());
            return null;
        } catch (ArquivoInvalidoException excecao) {
            LOG.info(excecao.getMessage());
            movedorArquivoRepository.moverParaInvalido(excecao.getArquivoPath());
            return null;
        } catch (Exception excecao) {
            LOG.error("Ocorreu um erro durante a leitura do arquivo de entrada", excecao);
            excecao.printStackTrace();
            return null;
        }
    }
}