package br.com.dbccompany.importadorarquivosbatch.cucumber.stepdefs.importararquivo;

import br.com.dbccompany.importadorarquivosbatch.cucumber.contexto.ImportadorArquivosContexto;
import br.com.dbccompany.importadorarquivosbatch.cucumber.funcionalidade.ImportadorArquivosFuncionalidade;
import br.com.dbccompany.importadorarquivosbatch.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.importadorarquivosbatch.cucumber.verificador.ImportadorArquivosVerificador;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class ImportarArquivoStepDefs extends StepDefs {

    @Autowired
    private ImportadorArquivosContexto importadorArquivosContexto;

    @Autowired
    private ImportadorArquivosFuncionalidade importadorArquivosFuncionalidade;

    @Autowired
    private ImportadorArquivosVerificador importadorArquivosVerificador;

    @Before
    public void inicializarContexto() throws Exception {
        importadorArquivosContexto.limparDiretorios();
    }

    @After
    public void finalizarContexto() throws Exception {
        importadorArquivosContexto.limparDiretorios();
    }

    @Dado("^que exista o arquivo \"([^\"]*)\" no diretorio de entrada$")
    public void queExistaOhArquivoEsperadoNoDiretorioDeEntrada(String nomeArquivoEntrada) throws IOException {
        importadorArquivosContexto.criarArquivoNoDiretorioDeEntrada(nomeArquivoEntrada);
    }

    @Quando("^importar o arquivo$")
    public void importarOhArquivo() throws Exception {
        importadorArquivosFuncionalidade.executarImportacao();
    }

    @Entao("^deveria criar o arquivo \"([^\"]*)\" no diretorio de saida$")
    public void deveriaCriarOhArquivoEsperadoNoDiretorioDeSaida(String nomeArquivoSaida) throws Exception {
        this.nomeArquivoSaida = nomeArquivoSaida;
        importadorArquivosVerificador.verificarArquivoSaida(nomeArquivoSaida);
    }

    @E("^deveria gravar dentro do arquivo de saida o conteudo consolidado \"([^\"]*)\"$")
    public void deveriaGravarDentroDoArquivoDeSaidaOhConteudoConsolidado(String conteudoArquivoSaida) throws Exception {
        importadorArquivosVerificador.verificarConteudoArquivoSaida(nomeArquivoSaida, conteudoArquivoSaida);
    }

    @E("^deveria excluir o arquivo \"([^\"]*)\" do diretorio de entrada$")
    public void deveriaExcluirOhArquivoEsperadoDoDiretorioDeEntrada(String nomeArquivoEntrada) throws Exception {
        importadorArquivosVerificador.verificarArquivoEntrada(nomeArquivoEntrada);
    }
}