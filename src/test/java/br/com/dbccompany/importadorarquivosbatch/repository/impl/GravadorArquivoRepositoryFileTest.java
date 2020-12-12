package br.com.dbccompany.importadorarquivosbatch.repository.impl;

import br.com.dbccompany.importadorarquivosbatch.ImportadorArquivosBatchApplication;
import br.com.dbccompany.importadorarquivosbatch.cucumber.contexto.ImportadorArquivosContexto;
import br.com.dbccompany.importadorarquivosbatch.cucumber.verificador.ImportadorArquivosVerificador;
import br.com.dbccompany.importadorarquivosbatch.repository.GravadorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RepositorioException;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static br.com.dbccompany.importadorarquivosbatch.fixture.DadosProcessamentoFixture.umDadosProcessamentoSucessoDbc;
import static br.com.dbccompany.importadorarquivosbatch.util.ConstanteTesteUtil.ARQUIVO_SUCESSO_DBC_DONE_DAT;
import static br.com.dbccompany.importadorarquivosbatch.util.ConstanteTesteUtil.CONTEUDO_ARQUIVO_SUCESSO_DBC_DONE_DAT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImportadorArquivosBatchApplication.class)
public class GravadorArquivoRepositoryFileTest {

    @Autowired
    private ImportadorArquivosContexto importadorArquivosContexto;

    @Autowired
    private GravadorArquivoRepository gravadorArquivoRepository;

    @Autowired
    private ImportadorArquivosVerificador importadorArquivosVerificador;

    @After
    public void finalizarContexto() {
        importadorArquivosContexto.excluirDiretorios();
    }

    @Test
    public void aoGravarDadoQueExistaDiretorioDeSaidaDeveriaGravarOhNomeIhConteudoEsperados() {
        importadorArquivosContexto.criarDiretorios();
        gravadorArquivoRepository.gravar(umDadosProcessamentoSucessoDbc());
        importadorArquivosVerificador.verificarArquivoSaida(ARQUIVO_SUCESSO_DBC_DONE_DAT);
        importadorArquivosVerificador.verificarConteudoArquivoSaida(ARQUIVO_SUCESSO_DBC_DONE_DAT, CONTEUDO_ARQUIVO_SUCESSO_DBC_DONE_DAT);
    }

    @Test(expected = RepositorioException.class)
    public void aoGravarDadoQueNaoExistaDiretorioDeSaidaDeveriaLancarUmaRepositorioException() {
        gravadorArquivoRepository.gravar(umDadosProcessamentoSucessoDbc());
    }
}