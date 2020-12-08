package br.com.dbccompany.importadorarquivosbatch.repository.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.repository.LeitorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.repository.parse.ArquivoParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.NenhumArquivoImportacaoException;
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
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

@Repository
public class LeitorArquivoRepositoryFile implements LeitorArquivoRepository {

    private static final String EXTENSAO_ARQUIVO = ".dat";
    private static final char SEPARADOR_REGISTROS = 'ç';

    private final Properties importacaoArquivosProperties;
    private final ArquivoParse arquivoParse;

    public LeitorArquivoRepositoryFile(Properties importacaoArquivosProperties,
                                       ArquivoParse arquivoParse) {
        this.importacaoArquivosProperties = importacaoArquivosProperties;
        this.arquivoParse = arquivoParse;
    }

    @Override
    public DadosLeitura lerArquivoNaoImportado() {
        try (Stream<Path> arquivosDiretorioEntrada = obterArquivosDiretorioEntrada()) {
            final Path arquivoPath = obterPrimeiroArquivoDatPorOrdemAlfabetica(arquivosDiretorioEntrada);
            final CSVReader arquivoReader = lerConteudoArquivo(arquivoPath);
            final List<Registro> registros = arquivoParse.parse(arquivoReader.readAll());
            return new DadosLeitura(arquivoPath, registros);
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
                .orElseThrow(() -> new NenhumArquivoImportacaoException());
    }

    private CSVReader lerConteudoArquivo(Path arquivoPath) throws FileNotFoundException {
        return new CSVReaderBuilder(gerarFileReader(arquivoPath))
                .withSkipLines(0)
                .withCSVParser(gerarCSVParserBuilder())
                .build();
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