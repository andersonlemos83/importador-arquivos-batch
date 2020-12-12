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

import static java.text.MessageFormat.format;
import static java.util.Comparator.reverseOrder;

@Component
public class ImportadorArquivosContexto {

    @Value("${importador-arquivos.data.root}")
    private String diretorioRoot;

    @Value("${importador-arquivos.data.in}")
    private String diretorioEntrada;

    @Value("${importador-arquivos.data.out}")
    private String diretorioSaida;

    public void criarDiretorios() {
        Arrays.asList(diretorioEntrada, diretorioSaida).forEach(this::criarDiretorio);
    }

    public void excluirDiretorios() {
        excluirDiretorioRecursivamente(diretorioRoot);
    }

    public Boolean existeArquivoSaida(String nomeArquivoSaida) {
        final Path arquivoSaida = Paths.get(diretorioSaida + "/" + nomeArquivoSaida);
        return Files.exists(arquivoSaida);
    }

    public Boolean existeArquivoEntrada(String nomeArquivoEntrada) {
        final Path arquivoEntrada = Paths.get(diretorioEntrada + "/" + nomeArquivoEntrada);
        return Files.exists(arquivoEntrada);
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

    private void criarDiretorio(String caminho) {
        String[] diretorios = caminho.split("/");
        String diretorioCompleto = "";
        for (int i = 0; i < diretorios.length; i++) {
            if (i == 0 && (".".equalsIgnoreCase(diretorios[i]) || diretorios[i].contains(":"))) {
                diretorioCompleto = diretorios[i];
            } else {
                diretorioCompleto = format("{0}/{1}", diretorioCompleto, diretorios[i]);
                File pasta = new File(diretorioCompleto);
                if (!pasta.exists()) {
                    pasta.mkdir();
                }
            }
        }
    }

    public void criarArquivoNoDiretorioDeEntrada(String nomeArquivoEntrada) {
        try {
            final Path arquivoOrigemPath = gerarPathResource("data/" + nomeArquivoEntrada);
            final Path arquivoDestinoPath = Paths.get(diretorioEntrada + "/" + nomeArquivoEntrada);
            Files.copy(arquivoOrigemPath, arquivoDestinoPath);
        } catch (Exception excecao) {
            throw new RuntimeException("Erro ao criar arquivo no diretório de entrada: " + nomeArquivoEntrada, excecao);
        }
    }

    private Path gerarPathResource(String nomeResource) throws IOException {
        return Paths.get(new DefaultResourceLoader().getResource(nomeResource).getURI());
    }

    private void excluirDiretorioRecursivamente(String diretorio) {
        if (!new File(diretorio).exists()) {
            return;
        }
        try (Stream<Path> arquivosPath = Files.walk(Paths.get(diretorio))) {
            arquivosPath.sorted(reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (Exception excecao) {
            throw new RuntimeException("Erro ao excluir diretório: " + diretorio, excecao);
        }
    }
}