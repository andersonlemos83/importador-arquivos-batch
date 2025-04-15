package br.com.dbccompany.importadorarquivosbatch.cucumber.contexto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Component
public class ImportadorArquivosContexto {

    private String diretorioRoot;
    private String diretorioEntrada;
    private String diretorioSaida;
    private String diretorioInvalido;

    public ImportadorArquivosContexto(@Value("${importador-arquivos.data.root}") String diretorioRoot,
                                      @Value("${importador-arquivos.data.in}") String diretorioEntrada,
                                      @Value("${importador-arquivos.data.out}") String diretorioSaida,
                                      @Value("${importador-arquivos.data.invalid}") String diretorioInvalido) {
        this.diretorioRoot = diretorioRoot;
        this.diretorioEntrada = diretorioEntrada;
        this.diretorioSaida = diretorioSaida;
        this.diretorioInvalido = diretorioInvalido;
    }

    public void criarDiretorios() {
        Arrays.asList(diretorioRoot, diretorioEntrada, diretorioSaida, diretorioInvalido).forEach(this::criarDiretorio);
    }

    public void excluirDiretorios() {
        excluirDiretorioRecursivamente(diretorioRoot);
    }

    public Boolean existeArquivoSaida(String nomeArquivoSaida) {
        final Path arquivoSaida = Paths.get(diretorioSaida + "/" + nomeArquivoSaida);
        return Files.exists(arquivoSaida);
    }

    public String obterConteudoArquivoSaida(String nomeArquivoSaida) {
        try {
            final Path arquivoSaida = Paths.get(diretorioSaida + "/" + nomeArquivoSaida);
            final List<String> registros = Files.readAllLines(arquivoSaida);
            return registros.toString()
                    .replaceAll("\\[", "")
                    .replaceAll("]", "");
        } catch (Exception excecao) {
            throw new RuntimeException("Erro ao obter o conteúdo do arquivos de saída: " + nomeArquivoSaida, excecao);
        }
    }

    public Boolean existeArquivoEntrada(String nomeArquivoEntrada) {
        final Path arquivoEntrada = Paths.get(diretorioEntrada + "/" + nomeArquivoEntrada);
        return Files.exists(arquivoEntrada);
    }

    public Boolean existeArquivoInvalido(String nomeArquivoInvalido) {
        final Path arquivoEntrada = Paths.get(diretorioInvalido + "/" + nomeArquivoInvalido);
        return Files.exists(arquivoEntrada);
    }

    private void criarDiretorio(String caminho) {
        try {
            Path diretorioPath = Paths.get(caminho);
            Files.createDirectories(diretorioPath);
        } catch (Exception excecao) {
            throw new RuntimeException("Erro ao criar diretório: " + caminho, excecao);
        }
    }

    public void criarArquivoNoDiretorioDeEntrada(String nomeArquivoEntrada) {
        try {
            final Path arquivoOrigemPath = gerarPathResource("data/" + nomeArquivoEntrada);
            final Path arquivoDestinoPath = Paths.get(diretorioEntrada + "/" + nomeArquivoEntrada);
            Files.copy(arquivoOrigemPath, arquivoDestinoPath, REPLACE_EXISTING);
        } catch (Exception excecao) {
            throw new RuntimeException("Erro ao criar arquivo no diretório de entrada: " + nomeArquivoEntrada, excecao);
        }
    }

    private Path gerarPathResource(String nomeResource) throws IOException {
        return Paths.get(new DefaultResourceLoader().getResource(nomeResource).getURI());
    }

    private void excluirDiretorioRecursivamente(String diretorio) {
        final Path path = Paths.get(diretorio);

        if (!Files.exists(path)) {
            return;
        }

        try (Stream<Path> arquivosPath = Files.walk(path)) {
            arquivosPath.map(Path::toFile).forEach(File::delete);
        } catch (Exception excecao) {
            throw new RuntimeException("Erro ao excluir diretório: " + diretorio, excecao);
        }
    }
}