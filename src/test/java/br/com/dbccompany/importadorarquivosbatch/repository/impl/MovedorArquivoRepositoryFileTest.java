package br.com.dbccompany.importadorarquivosbatch.repository.impl;

import br.com.dbccompany.importadorarquivosbatch.config.ImportadorArquivosConfiguration;
import br.com.dbccompany.importadorarquivosbatch.cucumber.contexto.ImportadorArquivosContexto;
import br.com.dbccompany.importadorarquivosbatch.cucumber.verificador.ImportadorArquivosVerificador;
import br.com.dbccompany.importadorarquivosbatch.repository.MovedorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RepositorioException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.dbccompany.importadorarquivosbatch.helper.util.ConstanteUtil.ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT;
import static br.com.dbccompany.importadorarquivosbatch.helper.util.ConstanteUtil.ARQUIVO_SUCESSO_DBC_DAT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ImportadorArquivosContexto.class, MovedorArquivoRepositoryFile.class, ImportadorArquivosVerificador.class, ImportadorArquivosConfiguration.class})
public class MovedorArquivoRepositoryFileTest {

    @Autowired
    private ImportadorArquivosContexto importadorArquivosContexto;

    @Autowired
    private MovedorArquivoRepository movedorArquivoRepository;

    @Autowired
    private ImportadorArquivosVerificador importadorArquivosVerificador;

    @BeforeEach
    public void inicializarContexto() {
        importadorArquivosContexto.excluirDiretorios();
    }

    @AfterEach
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

    @Test
    public void aoMoverParaInvalidoDadoQueNaoExistaArquivoDeEntradaDeveriaLancarUmaRepositorioException() {
        RepositorioException thrown = assertThrows(RepositorioException.class, () -> movedorArquivoRepository.moverParaInvalido(ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT));
        assertEquals("java.nio.file.NoSuchFileException: .\\data\\in\\sucesso-dbc.dat", thrown.getMessage());
    }
}