package br.com.dbccompany.importadorarquivosbatch.repository.impl;

import br.com.dbccompany.importadorarquivosbatch.ImportadorArquivosBatchApplication;
import br.com.dbccompany.importadorarquivosbatch.cucumber.contexto.ImportadorArquivosContexto;
import br.com.dbccompany.importadorarquivosbatch.domain.Arquivo;
import br.com.dbccompany.importadorarquivosbatch.repository.LeitorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.NenhumArquivoImportacaoException;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RepositorioException;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static br.com.dbccompany.importadorarquivosbatch.fixture.RegistroFixture.umaListaRegistrosArraySucessoDbc;
import static br.com.dbccompany.importadorarquivosbatch.util.ConstanteTesteUtil.*;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImportadorArquivosBatchApplication.class)
public class LeitorArquivoRepositoryFileTest {

    @Autowired
    private ImportadorArquivosContexto importadorArquivosContexto;

    @Autowired
    private LeitorArquivoRepository leitorArquivoRepository;

    @After
    public void finalizarContexto() {
        importadorArquivosContexto.excluirDiretorios();
    }

    @Test
    public void aoLerArquivoNaoImportadoDadoQueExistaArquivoDeEntradaDeveriaRetornarOsDadosEsperados() {
        importadorArquivosContexto.criarDiretorios();
        importadorArquivosContexto.criarArquivoNoDiretorioDeEntrada(ARQUIVO_SUCESSO_DBC_DAT);
        final Arquivo arquivo = leitorArquivoRepository.lerArquivoNaoImportado();
        assertEquals(ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT, arquivo.getArquivoPath());
        assertRegistrosArrays(umaListaRegistrosArraySucessoDbc(), arquivo.getRegistrosArray());
    }

    @Test(expected = NenhumArquivoImportacaoException.class)
    public void aoLerArquivoNaoImportadoDadoQueNaoExistaArquivoDeEntradaDeveriaLancarUmaNenhumArquivoImportacaoException() {
        importadorArquivosContexto.criarDiretorios();
        importadorArquivosContexto.criarArquivoNoDiretorioDeEntrada(ARQUIVO_SUCESSO_DBC_TXT);
        leitorArquivoRepository.lerArquivoNaoImportado();
    }

    @Test
    public void aoLerArquivoNaoImportadoDadoQueNaoExistaArquivoDeEntradaDeveriaLancarUmaExcecaoComAhMensagemNaoExisteNenhumArquivoParaImportacao() {
        try {
            importadorArquivosContexto.criarDiretorios();
            importadorArquivosContexto.criarArquivoNoDiretorioDeEntrada(ARQUIVO_SUCESSO_DBC_TXT);
            leitorArquivoRepository.lerArquivoNaoImportado();
            fail("Deveria lançar uma excecção...");
        } catch (NenhumArquivoImportacaoException excecao) {
            assertEquals("Não existe nenhum arquivo para importação.", excecao.getMessage());
        }
    }

    @Test(expected = RepositorioException.class)
    public void aoLerArquivoNaoImportadoDadoQueNaoExistaDiretorioDeEntradaDeveriaLancarUmaRepositorioException() {
        leitorArquivoRepository.lerArquivoNaoImportado();
    }

    private void assertRegistrosArrays(List<String[]> registrosArrayEsperado, List<String[]> registrosArrayRetornado) {
        List<List<String>> registrosEsperados = registrosArrayEsperado.stream().map(Arrays::asList).collect(toList());
        List<List<String>> registrosRetornados = registrosArrayRetornado.stream().map(Arrays::asList).collect(toList());
        assertEquals(registrosEsperados, registrosRetornados);
    }
}