package br.com.dbccompany.importadorarquivosbatch.repository.impl;

import br.com.dbccompany.importadorarquivosbatch.ImportadorArquivosBatchApplication;
import br.com.dbccompany.importadorarquivosbatch.cucumber.contexto.ImportadorArquivosContexto;
import br.com.dbccompany.importadorarquivosbatch.cucumber.verificador.ImportadorArquivosVerificador;
import br.com.dbccompany.importadorarquivosbatch.repository.ExcluidorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RepositorioException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImportadorArquivosBatchApplication.class)
public class ExcluidorArquivoRepositoryFileTest {

    @Autowired
    private ImportadorArquivosContexto importadorArquivosContexto;

    @Autowired
    private ExcluidorArquivoRepository excluidorArquivoRepository;

    @Autowired
    private ImportadorArquivosVerificador importadorArquivosVerificador;

    private String nomeArquivoEntrada = "sucesso-dbc.dat";
    private Path arquivoEntradaPath = Paths.get("./data/in/sucesso-dbc.dat");

    @Before
    public void inicializarContexto() {
        importadorArquivosContexto.criarDiretorios();
    }

    @After
    public void finalizarContexto() {
        importadorArquivosContexto.excluirDiretorios();
    }

    @Test
    public void aoExcluirDadoQueExistaArquivoDeEntradaDeveriaExcluirOhArquivoDeEntrada() {
        importadorArquivosContexto.criarArquivoNoDiretorioDeEntrada(nomeArquivoEntrada);
        excluidorArquivoRepository.excluir(arquivoEntradaPath);
        importadorArquivosVerificador.verificarArquivoEntrada(nomeArquivoEntrada);
    }

    @Test(expected = RepositorioException.class)
    public void aoExcluirDadoQueNaoExistaArquivoDeEntradaDeveriaLancarUmaRepositorioException() {
        excluidorArquivoRepository.excluir(arquivoEntradaPath);
    }
}
