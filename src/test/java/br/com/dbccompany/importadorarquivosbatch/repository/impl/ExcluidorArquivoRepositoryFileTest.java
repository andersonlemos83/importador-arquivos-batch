package br.com.dbccompany.importadorarquivosbatch.repository.impl;

import br.com.dbccompany.importadorarquivosbatch.config.ImportadorArquivosConfiguration;
import br.com.dbccompany.importadorarquivosbatch.cucumber.contexto.ImportadorArquivosContexto;
import br.com.dbccompany.importadorarquivosbatch.cucumber.verificador.ImportadorArquivosVerificador;
import br.com.dbccompany.importadorarquivosbatch.repository.ExcluidorArquivoRepository;
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

import java.nio.file.Path;
import java.nio.file.Paths;

import static br.com.dbccompany.importadorarquivosbatch.helper.util.ConstanteUtil.ARQUIVO_SUCESSO_DBC_DAT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ImportadorArquivosContexto.class, ExcluidorArquivoRepositoryFile.class, ImportadorArquivosVerificador.class, ImportadorArquivosConfiguration.class})
public class ExcluidorArquivoRepositoryFileTest {

    @Autowired
    private ImportadorArquivosContexto importadorArquivosContexto;

    @Autowired
    private ExcluidorArquivoRepository excluidorArquivoRepository;

    @Autowired
    private ImportadorArquivosVerificador importadorArquivosVerificador;

    @Value("${importador-arquivos.data.in}")
    private String diretorioEntrada;

    @BeforeEach
    public void inicializarContexto() {
        importadorArquivosContexto.excluirDiretorios();
        importadorArquivosContexto.criarDiretorios();
    }

    @AfterEach
    public void finalizarContexto() {
        importadorArquivosContexto.excluirDiretorios();
    }

    @Test
    public void aoExcluirDadoQueExistaArquivoDeEntradaDeveriaExcluirOhArquivoDeEntrada() {
        importadorArquivosContexto.criarArquivoNoDiretorioDeEntrada(ARQUIVO_SUCESSO_DBC_DAT);
        Path arquivo = Paths.get(diretorioEntrada + "/" + ARQUIVO_SUCESSO_DBC_DAT);
        excluidorArquivoRepository.excluir(arquivo);
        importadorArquivosVerificador.verificarSeNaoExisteArquivoEntrada(ARQUIVO_SUCESSO_DBC_DAT);
    }

    @Test
    public void aoExcluirDadoQueNaoExistaArquivoDeEntradaDeveriaLancarUmaRepositorioException() {
        Path arquivo = Paths.get(diretorioEntrada + "/" + ARQUIVO_SUCESSO_DBC_DAT);
        String mensagemEsperada = "java.nio.file.NoSuchFileException: " + arquivo;
        RepositorioException thrown = assertThrows(RepositorioException.class, () -> excluidorArquivoRepository.excluir(arquivo));
        assertEquals(mensagemEsperada, thrown.getMessage());
    }
}