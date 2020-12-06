package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.DadosImportacao;
import br.com.dbccompany.importadorarquivosbatch.repository.LeitorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.InformacaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
public class ImportadorArquivosItemReader implements ItemReader<DadosImportacao> {

    private static final Logger LOG = LoggerFactory.getLogger(ImportadorArquivosItemReader.class);

    private final LeitorArquivoRepository leitorArquivoRepository;

    public ImportadorArquivosItemReader(LeitorArquivoRepository leitorArquivoRepository) {
        this.leitorArquivoRepository = leitorArquivoRepository;
    }

    @Override
    public DadosImportacao read() {
        try {
            List<Path> arquivos = leitorArquivoRepository.lerArquivosNaoImportados();
            if (arquivos.isEmpty()) {
                return null;
            }
            return new DadosImportacao(arquivos);
        } catch (InformacaoException excecao) {
            LOG.info(excecao.getMessage());
            return null;
        } catch (Exception excecao) {
            LOG.error("Ocorreu um erro durante a leitura dos arquivos", excecao);
            excecao.printStackTrace();
            return null;
        }
    }
}