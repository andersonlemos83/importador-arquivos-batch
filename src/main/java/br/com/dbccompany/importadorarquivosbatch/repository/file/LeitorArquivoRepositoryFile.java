package br.com.dbccompany.importadorarquivosbatch.repository.file;

import br.com.dbccompany.importadorarquivosbatch.domain.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.repository.LeitorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.InformacaoException;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RepositorioException;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Stream;

@Repository
public class LeitorArquivoRepositoryFile implements LeitorArquivoRepository {

    private final Properties importacaoArquivosProperties;

    public LeitorArquivoRepositoryFile(Properties importacaoArquivosProperties) {
        this.importacaoArquivosProperties = importacaoArquivosProperties;
    }

    @Override
    public DadosLeitura lerArquivoNaoImportado() {
        try (Stream<Path> arquivosDiretorioEntrada = obterArquivosDiretorioEntrada()) {
            Path arquivoPath = obterPrimeiroArquivoDatPorOrdemAlfabetica(arquivosDiretorioEntrada);
            CSVReader arquivoReader = lerConteudoArquivo(arquivoPath);
            return new DadosLeitura(arquivoPath, arquivoReader.readAll());
        } catch (IOException | CsvException excecao) {
            throw new RepositorioException(excecao);
        }
    }

    private Stream<Path> obterArquivosDiretorioEntrada() throws IOException {
        final String diretorioEntrada = importacaoArquivosProperties.getProperty("diretorioEntrada");
        return Files.walk(Paths.get(diretorioEntrada));
    }

    private Path obterPrimeiroArquivoDatPorOrdemAlfabetica(Stream<Path> arquivosDiretorioEntrada) {
        return arquivosDiretorioEntrada
                .sorted()
                .filter(path -> path.toString().endsWith(".dat"))
                .findFirst()
                .orElseThrow(() -> new InformacaoException("Não existe nenhum arquivo para importação."));
    }

    private CSVReader lerConteudoArquivo(Path arquivoPath) throws FileNotFoundException {
        FileReader fileReader = new FileReader(arquivoPath.toString());

        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator('ç')
                .withIgnoreQuotations(true)
                .build();

        return new CSVReaderBuilder(fileReader)
                .withSkipLines(0)
                .withCSVParser(csvParser)
                .build();
    }
}