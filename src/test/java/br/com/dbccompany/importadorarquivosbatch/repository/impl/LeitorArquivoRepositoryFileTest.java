package br.com.dbccompany.importadorarquivosbatch.repository.impl;

import br.com.dbccompany.importadorarquivosbatch.config.ImportadorArquivosConfiguration;
import br.com.dbccompany.importadorarquivosbatch.cucumber.contexto.ImportadorArquivosContexto;
import br.com.dbccompany.importadorarquivosbatch.domain.Arquivo;
import br.com.dbccompany.importadorarquivosbatch.repository.LeitorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.NenhumArquivoImportacaoException;
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
import java.util.Arrays;
import java.util.List;

import static br.com.dbccompany.importadorarquivosbatch.helper.fixture.RegistroFixture.umaListaRegistrosArraySucessoDbc;
import static br.com.dbccompany.importadorarquivosbatch.helper.util.ConstanteUtil.ARQUIVO_SUCESSO_DBC_DAT;
import static br.com.dbccompany.importadorarquivosbatch.helper.util.ConstanteUtil.ARQUIVO_SUCESSO_DBC_TXT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ImportadorArquivosContexto.class, LeitorArquivoRepositoryFile.class, ImportadorArquivosConfiguration.class})
public class LeitorArquivoRepositoryFileTest {

    @Autowired
    private ImportadorArquivosContexto importadorArquivosContexto;

    @Autowired
    private LeitorArquivoRepository leitorArquivoRepository;

    @Value("${importador-arquivos.data.in}")
    private String diretorioEntrada;

    @BeforeEach
    public void inicializarContexto() {
        importadorArquivosContexto.excluirDiretorios();
    }

    @AfterEach
    public void finalizarContexto() {
        importadorArquivosContexto.excluirDiretorios();
    }

    @Test
    public void aoLerArquivoNaoImportadoDadoQueExistaArquivoDeEntradaDeveriaRetornarOsDadosEsperados() {
        importadorArquivosContexto.criarDiretorios();
        importadorArquivosContexto.criarArquivoNoDiretorioDeEntrada(ARQUIVO_SUCESSO_DBC_DAT);
        Path arquivoPathExperado = Paths.get(diretorioEntrada + "/" + ARQUIVO_SUCESSO_DBC_DAT);
        Arquivo arquivoRetornado = leitorArquivoRepository.lerArquivoNaoImportado();
        assertEquals(arquivoPathExperado, arquivoRetornado.getArquivoPath());
        assertRegistrosArrays(umaListaRegistrosArraySucessoDbc(), arquivoRetornado.getRegistrosArray());
    }

    @Test
    public void aoLerArquivoNaoImportadoDadoQueNaoExistaArquivoDeEntradaDeveriaLancarUmaNenhumArquivoImportacaoException() {
        importadorArquivosContexto.criarDiretorios();
        importadorArquivosContexto.criarArquivoNoDiretorioDeEntrada(ARQUIVO_SUCESSO_DBC_TXT);

        NenhumArquivoImportacaoException thrown = assertThrows(NenhumArquivoImportacaoException.class,
                () -> leitorArquivoRepository.lerArquivoNaoImportado());

        assertEquals("Não existe nenhum arquivo para importação.", thrown.getMessage());
    }

    @Test
    public void aoLerArquivoNaoImportadoDadoQueNaoExistaDiretorioDeEntradaDeveriaLancarUmaRepositorioException() {
        String mensagemEsperada = "java.nio.file.NoSuchFileException: " + Paths.get(diretorioEntrada);
        RepositorioException thrown = assertThrows(RepositorioException.class, () -> leitorArquivoRepository.lerArquivoNaoImportado());
        assertEquals(mensagemEsperada, thrown.getMessage());
    }

    private void assertRegistrosArrays(List<String[]> registrosArrayEsperado, List<String[]> registrosArrayRetornado) {
        List<List<String>> registrosEsperados = registrosArrayEsperado.stream().map(Arrays::asList).toList();
        List<List<String>> registrosRetornados = registrosArrayRetornado.stream().map(Arrays::asList).toList();
        assertEquals(registrosEsperados, registrosRetornados);
    }
}