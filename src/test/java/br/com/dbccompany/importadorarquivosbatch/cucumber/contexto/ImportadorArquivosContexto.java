package br.com.dbccompany.importadorarquivosbatch.cucumber.contexto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ImportadorArquivosContexto {

    @Value("${importador-arquivos.data.in}")
    private String diretorioEntrada;

    @Value("${importador-arquivos.data.out}")
    private String diretorioSaida;

    @Value("${importador-arquivos.data.test}")
    private String diretorioTest;

    public void limparDiretorios() throws IOException {
        for (String diretorio : Arrays.asList(diretorioEntrada, diretorioSaida)) {
            limparDiretorio(diretorio);
        }
    }

    public void criarArquivoNoDiretorioDeEntrada(String nomeArquivoEntrada) throws IOException {
        final Path arquivoOrigemPath = Paths.get(diretorioTest + "/" + nomeArquivoEntrada);
        final Path arquivoDestinoPath = Paths.get(diretorioEntrada + "/" + nomeArquivoEntrada);
        Files.copy(arquivoOrigemPath, arquivoDestinoPath);
    }

    private void limparDiretorio(String diretorio) throws IOException {
        final List<Path> arquivosPath = obterTodosArquivosDiretorio(diretorio);
        for (Path arquivoPath : arquivosPath) {
            Files.deleteIfExists(arquivoPath);
        }
    }

    private List<Path> obterTodosArquivosDiretorio(String diretorio) throws IOException {
        final Path diretorioPath = Paths.get(diretorio);
        return Files.walk(diretorioPath)
                .filter(path -> path.compareTo(diretorioPath) != 0)
                .collect(toList());
    }

    public Boolean existeArquivoSaida(String nomeArquivoSaida) {
        final Path arquivoSaida = Paths.get(diretorioSaida + "/" + nomeArquivoSaida);
        return Files.exists(arquivoSaida);
    }

    public Boolean existeArquivoEntrada(String nomeArquivoEntrada) {
        final Path arquivoEntrada = Paths.get(diretorioEntrada + "/" + nomeArquivoEntrada);
        return Files.exists(arquivoEntrada);
    }

    public String obterConteudoArquivoSaida(String nomeArquivoSaida) throws IOException {
        final Path arquivoSaida = Paths.get(diretorioSaida + "/" + nomeArquivoSaida);
        final List<String> registros = Files.readAllLines(arquivoSaida);
        return registros.toString()
                .replaceAll("\\[", "")
                .replaceAll("]", "");
    }
}