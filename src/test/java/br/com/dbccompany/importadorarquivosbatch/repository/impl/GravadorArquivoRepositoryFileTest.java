package br.com.dbccompany.importadorarquivosbatch.repository.impl;

import br.com.dbccompany.importadorarquivosbatch.config.ImportadorArquivosConfiguration;
import br.com.dbccompany.importadorarquivosbatch.cucumber.contexto.ImportadorArquivosContexto;
import br.com.dbccompany.importadorarquivosbatch.cucumber.verificador.ImportadorArquivosVerificador;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
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
        String mensagemEsperada = "java.nio.file.NoSuchFileException: " + Paths.get(diretorioSaida) + "\\" + ARQUIVO_SUCESSO_DBC_DONE_DAT;
        RepositorioException thrown = assertThrows(RepositorioException.class, () -> gravadorArquivoRepository.gravar(umDadosProcessamentoSucessoDbc()));
        assertEquals(mensagemEsperada, thrown.getMessage());
    }
}