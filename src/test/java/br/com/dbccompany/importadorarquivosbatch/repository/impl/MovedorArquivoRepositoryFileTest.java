package br.com.dbccompany.importadorarquivosbatch.repository.impl;

import br.com.dbccompany.importadorarquivosbatch.ImportadorArquivosBatchApplication;
import br.com.dbccompany.importadorarquivosbatch.cucumber.contexto.ImportadorArquivosContexto;
import br.com.dbccompany.importadorarquivosbatch.cucumber.verificador.ImportadorArquivosVerificador;
import br.com.dbccompany.importadorarquivosbatch.repository.MovedorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RepositorioException;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static br.com.dbccompany.importadorarquivosbatch.util.ConstanteTesteUtil.ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT;
import static br.com.dbccompany.importadorarquivosbatch.util.ConstanteTesteUtil.ARQUIVO_SUCESSO_DBC_DAT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImportadorArquivosBatchApplication.class)
public class MovedorArquivoRepositoryFileTest {

    @Autowired
    private ImportadorArquivosContexto importadorArquivosContexto;

    @Autowired
    private MovedorArquivoRepository movedorArquivoRepository;

    @Autowired
    private ImportadorArquivosVerificador importadorArquivosVerificador;

    @After
    public void finalizarContexto() {
        importadorArquivosContexto.excluirDiretorios();
    }

    @Test
    public void aoMoverParaInvalidoDadoQueExistaArquivoDeEntradaDeveriaMoverOhArquivoDeEntradaParaInvalido() {
        importadorArquivosContexto.criarDiretorios();
        importadorArquivosContexto.criarArquivoNoDiretorioDeEntrada(ARQUIVO_SUCESSO_DBC_DAT);
        movedorArquivoRepository.moverParaInvalido(ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT);
        importadorArquivosVerificador.verificarSeExisteArquivoInvalido(ARQUIVO_SUCESSO_DBC_DAT);
    }

    @Test(expected = RepositorioException.class)
    public void aoMoverParaInvalidoDadoQueNaoExistaArquivoDeEntradaDeveriaLancarUmaRepositorioException() {
        movedorArquivoRepository.moverParaInvalido(ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT);
    }
}