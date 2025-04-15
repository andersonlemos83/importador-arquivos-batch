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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static br.com.dbccompany.importadorarquivosbatch.helper.fixture.RegistroFixture.umaListaRegistrosArraySucessoDbc;
import static br.com.dbccompany.importadorarquivosbatch.helper.util.ConstanteUtil.*;
import static java.util.stream.Collectors.toList;
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
        Arquivo arquivo = leitorArquivoRepository.lerArquivoNaoImportado();
        assertEquals(ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT, arquivo.getArquivoPath());
        assertRegistrosArrays(umaListaRegistrosArraySucessoDbc(), arquivo.getRegistrosArray());
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
        RepositorioException thrown = assertThrows(RepositorioException.class, () -> leitorArquivoRepository.lerArquivoNaoImportado());
        assertEquals("java.nio.file.NoSuchFileException: .\\data\\in", thrown.getMessage());
    }

    private void assertRegistrosArrays(List<String[]> registrosArrayEsperado, List<String[]> registrosArrayRetornado) {
        List<List<String>> registrosEsperados = registrosArrayEsperado.stream().map(Arrays::asList).collect(toList());
        List<List<String>> registrosRetornados = registrosArrayRetornado.stream().map(Arrays::asList).collect(toList());
        assertEquals(registrosEsperados, registrosRetornados);
    }
}