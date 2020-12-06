package br.com.dbccompany.importadorarquivosbatch.repository.file;

import br.com.dbccompany.importadorarquivosbatch.repository.LeitorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RepositorioException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class LeitorArquivoRepositoryFile implements LeitorArquivoRepository {

    private final Properties importacaoArquivosProperties;

    public LeitorArquivoRepositoryFile(Properties importacaoArquivosProperties) {
        this.importacaoArquivosProperties = importacaoArquivosProperties;
    }

    @Override
    public List<Path> lerArquivosNaoImportados() {
        try (Stream<Path> arquivosDiretorioEntrada = obterArquivosDiretorioEntrada()) {
            return arquivosDiretorioEntrada.sorted().filter(path -> path.toString().endsWith(".dat")).collect(Collectors.toList());
        } catch (IOException excecao) {
            throw new RepositorioException(excecao);
        }
    }

    private Stream<Path> obterArquivosDiretorioEntrada() throws IOException {
        return Files.walk(Paths.get(obterDiretorioEntrada()));
    }

    private String obterDiretorioEntrada() {
        return importacaoArquivosProperties.getProperty("diretorioEntrada");
    }
}