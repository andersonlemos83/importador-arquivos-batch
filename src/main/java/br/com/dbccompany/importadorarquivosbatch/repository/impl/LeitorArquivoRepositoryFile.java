package br.com.dbccompany.importadorarquivosbatch.repository.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.Arquivo;
import br.com.dbccompany.importadorarquivosbatch.repository.LeitorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.NenhumArquivoImportacaoException;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RepositorioException;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

@Repository
@AllArgsConstructor
public class LeitorArquivoRepositoryFile implements LeitorArquivoRepository {

    private static final String EXTENSAO_ARQUIVO = ".dat";
    private static final char SEPARADOR_REGISTROS = 'ç';

    private final Properties importacaoArquivosProperties;

    @Override
    public Arquivo lerArquivoNaoImportado() {
        try (Stream<Path> arquivosDiretorioEntrada = obterArquivosDiretorioEntrada()) {
            final Path arquivoPath = obterPrimeiroArquivoDatPorOrdemAlfabetica(arquivosDiretorioEntrada);
            final List<String[]> conteudoArquivo = lerConteudoArquivo(arquivoPath);
            return new Arquivo(arquivoPath, conteudoArquivo);
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
                .filter(path -> path.toString().endsWith(EXTENSAO_ARQUIVO))
                .findFirst()
                .orElseThrow(NenhumArquivoImportacaoException::new);
    }

    private List<String[]> lerConteudoArquivo(Path arquivoPath) throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReaderBuilder(gerarFileReader(arquivoPath)).withSkipLines(0).withCSVParser(gerarCSVParserBuilder()).build()) {
            return csvReader.readAll();
        }
    }

    private FileReader gerarFileReader(Path arquivoPath) throws FileNotFoundException {
        return new FileReader(arquivoPath.toString());
    }

    private CSVParser gerarCSVParserBuilder() {
        return new CSVParserBuilder()
                .withSeparator(SEPARADOR_REGISTROS)
                .withIgnoreQuotations(true)
                .build();
    }
}