package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.repository.LeitorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.InformacaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
public class ImportadorArquivosItemReader implements ItemReader<DadosLeitura> {

    private static final Logger LOG = LoggerFactory.getLogger(ImportadorArquivosItemReader.class);

    private final LeitorArquivoRepository leitorArquivoRepository;

    public ImportadorArquivosItemReader(LeitorArquivoRepository leitorArquivoRepository) {
        this.leitorArquivoRepository = leitorArquivoRepository;
    }

    @Override
    public DadosLeitura read() {
        try {
            return leitorArquivoRepository.lerArquivoNaoImportado();
        } catch (InformacaoException excecao) {
            LOG.info(excecao.getMessage());
            return null;
        } catch (Exception excecao) {
            LOG.error("Ocorreu um erro durante a leitura do arquivo de entrada", excecao);
            excecao.printStackTrace();
            return null;
        }
    }
}