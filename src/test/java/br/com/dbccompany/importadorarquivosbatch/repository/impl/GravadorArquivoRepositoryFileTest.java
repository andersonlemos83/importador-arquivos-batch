package br.com.dbccompany.importadorarquivosbatch.repository.impl;

import br.com.dbccompany.importadorarquivosbatch.config.ImportadorArquivosConfiguration;
import br.com.dbccompany.importadorarquivosbatch.cucumber.contexto.ImportadorArquivosContexto;
import br.com.dbccompany.importadorarquivosbatch.cucumber.verificador.ImportadorArquivosVerificador;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.repository.GravadorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RepositorioException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Paths;

import static br.com.dbccompany.importadorarquivosbatch.helper.fixture.DadosProcessamentoFixture.umDadosProcessamentoSucessoDbc;
import static br.com.dbccompany.importadorarquivosbatch.helper.util.ConstanteUtil.ARQUIVO_SUCESSO_DBC_DONE_DAT;
import static br.com.dbccompany.importadorarquivosbatch.helper.util.ConstanteUtil.CONTEUDO_ARQUIVO_SUCESSO_DBC_DONE_DAT;
import static java.io.File.separator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Public required for JUnit test suite
// Add at least one assertion to this test case.
@SuppressWarnings({"java:S5786", "java:S2699"})
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ImportadorArquivosContexto.class, GravadorArquivoRepositoryFile.class, ImportadorArquivosVerificador.class, ImportadorArquivosConfiguration.class})
public class GravadorArquivoRepositoryFileTest {

    @Autowired
    private ImportadorArquivosContexto importadorArquivosContexto;

    @Autowired
    private GravadorArquivoRepository gravadorArquivoRepository;

    @Autowired
    private ImportadorArquivosVerificador importadorArquivosVerificador;

    @Value("${importador-arquivos.data.out}")
    private String diretorioSaida;

    @BeforeEach
    public void inicializarContexto() {
        importadorArquivosContexto.excluirDiretorios();
    }

    @AfterEach
    public void finalizarContexto() {
        importadorArquivosContexto.excluirDiretorios();
    }

    @Test
    public void aoGravarDadoQueExistaDiretorioDeSaidaDeveriaGravarOhNomeIhConteudoEsperados() {
        importadorArquivosContexto.criarDiretorios();
        gravadorArquivoRepository.gravar(umDadosProcessamentoSucessoDbc());
        importadorArquivosVerificador.verificarSeExisteArquivoSaida(ARQUIVO_SUCESSO_DBC_DONE_DAT);
        importadorArquivosVerificador.verificarConteudoArquivoSaida(ARQUIVO_SUCESSO_DBC_DONE_DAT, CONTEUDO_ARQUIVO_SUCESSO_DBC_DONE_DAT);
    }

    @Test
    public void aoGravarDadoQueNaoExistaDiretorioDeSaidaDeveriaLancarUmaRepositorioException() {
        String mensagemEsperada = "java.nio.file.NoSuchFileException: " + Paths.get(diretorioSaida) + separator + ARQUIVO_SUCESSO_DBC_DONE_DAT;
        DadosProcessamento dadosProcessamento = umDadosProcessamentoSucessoDbc();
        RepositorioException thrown = assertThrows(RepositorioException.class, () -> gravadorArquivoRepository.gravar(dadosProcessamento));
        assertEquals(mensagemEsperada, thrown.getMessage());
    }
}