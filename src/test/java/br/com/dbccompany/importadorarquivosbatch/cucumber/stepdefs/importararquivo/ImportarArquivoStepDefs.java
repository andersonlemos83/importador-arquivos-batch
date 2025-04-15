package br.com.dbccompany.importadorarquivosbatch.cucumber.stepdefs.importararquivo;

import br.com.dbccompany.importadorarquivosbatch.cucumber.contexto.ImportadorArquivosContexto;
import br.com.dbccompany.importadorarquivosbatch.cucumber.funcionalidade.ImportadorArquivosFuncionalidade;
import br.com.dbccompany.importadorarquivosbatch.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.importadorarquivosbatch.cucumber.verificador.ImportadorArquivosVerificador;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ImportarArquivoStepDefs extends StepDefs {

    private final ImportadorArquivosContexto importadorArquivosContexto;
    private final ImportadorArquivosFuncionalidade importadorArquivosFuncionalidade;
    private final ImportadorArquivosVerificador importadorArquivosVerificador;

    @Dado("^que exista o arquivo \"([^\"]*)\" no diretorio de entrada$")
    public void queExistaOhArquivoEsperadoNoDiretorioDeEntrada(String nomeArquivoEntrada) {
        importadorArquivosContexto.criarArquivoNoDiretorioDeEntrada(nomeArquivoEntrada);
    }

    @Quando("^importar o arquivo$")
    public void importarOhArquivo() throws Exception {
        importadorArquivosFuncionalidade.executarImportacao();
    }

    @Entao("^deveria criar o arquivo \"([^\"]*)\" no diretorio de saida$")
    public void deveriaCriarOhArquivoEsperadoNoDiretorioDeSaida(String nomeArquivoSaida) {
        this.nomeArquivoSaida = nomeArquivoSaida;
        importadorArquivosVerificador.verificarSeExisteArquivoSaida(nomeArquivoSaida);
    }

    @E("^deveria gravar dentro do arquivo de saida o conteudo consolidado \"([^\"]*)\"$")
    public void deveriaGravarDentroDoArquivoDeSaidaOhConteudoConsolidado(String conteudoArquivoSaida) {
        importadorArquivosVerificador.verificarConteudoArquivoSaida(nomeArquivoSaida, conteudoArquivoSaida);
    }

    @E("^deveria excluir o arquivo \"([^\"]*)\" do diretorio de entrada$")
    public void deveriaExcluirOhArquivoEsperadoDoDiretorioDeEntrada(String nomeArquivoEntrada) {
        importadorArquivosVerificador.verificarSeNaoExisteArquivoEntrada(nomeArquivoEntrada);
    }

    @Entao("^deveria criar o arquivo \"([^\"]*)\" no diretorio de invalido$")
    public void deveriaCriarOhArquivoEsperadoNoDiretorioDeInvalido(String nomeArquivoInvalido) {
        importadorArquivosVerificador.verificarSeExisteArquivoInvalido(nomeArquivoInvalido);
    }
}