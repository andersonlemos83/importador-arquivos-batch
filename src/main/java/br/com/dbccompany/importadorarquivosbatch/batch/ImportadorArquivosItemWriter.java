package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.DadosImportacao;
import br.com.dbccompany.importadorarquivosbatch.repository.GravadorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.repository.RemovedorArquivoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class ImportadorArquivosItemWriter implements ItemWriter<DadosImportacao> {

    private static final Logger LOG = LoggerFactory.getLogger(ImportadorArquivosItemWriter.class);

    private final GravadorArquivoRepository gravadorArquivoRepository;
    private final RemovedorArquivoRepository removedorArquivoRepository;

    public ImportadorArquivosItemWriter(GravadorArquivoRepository gravadorArquivoRepository,
                                        RemovedorArquivoRepository removedorArquivoRepository) {
        this.removedorArquivoRepository = removedorArquivoRepository;
        this.gravadorArquivoRepository = gravadorArquivoRepository;
    }

    @Override
    public void write(List<? extends DadosImportacao> dadosImportacoes) {
        List<Path> arquivos = obterArquivos(dadosImportacoes);
        for (Path arquivo : arquivos) {
            processarDados(arquivo);
        }
    }

    private List<Path> obterArquivos(List<? extends DadosImportacao> dadosImportacoes) {
        List<Path> arquivos = new ArrayList<>();
        for (DadosImportacao dadosImportacao : dadosImportacoes) {
            arquivos.addAll(dadosImportacao.getArquivos());
        }
        return arquivos;
    }

    private void processarDados(Path arquivo) {
        gravadorArquivoRepository.gravar(arquivo);
        removedorArquivoRepository.remover(arquivo);
    }
}