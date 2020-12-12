package br.com.dbccompany.importadorarquivosbatch.service.parse.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.fixture.VendaFixture;
import br.com.dbccompany.importadorarquivosbatch.service.parse.ArquivoParse;
import br.com.dbccompany.importadorarquivosbatch.service.parse.factory.RegistroParseFactory;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.ArquivoInvalidoException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static br.com.dbccompany.importadorarquivosbatch.fixture.ArquivoFixture.*;
import static br.com.dbccompany.importadorarquivosbatch.util.ConstanteTesteUtil.ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT;
import static java.util.Arrays.asList;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;

@RunWith(MockitoJUnitRunner.class)
public class ArquivoParseImplTest {

    private ArquivoParse arquivoParse;

    @Mock
    private RegistroParseFactory registroParseFactoryMock;

    @Mock
    private RegistroParse registroParseMock;

    private Registro registro;

    @Before
    public void inicializarContexto() {
        arquivoParse = new ArquivoParseImpl(registroParseFactoryMock);

        registro = VendaFixture.umaVendaQualquer();
    }

    @Test
    public void aoFazerParseComSucessoDadoQueSejaInformadoApenasUmRegistroDeveriaRealizarParseDeApenasUmRegistro() {
        configurarMocksComSucesso();
        arquivoParse.parse(umArquivoComUmRegistro());
        Mockito.verify(registroParseMock, atLeast(1)).parse(any(String[].class));
    }

    @Test
    public void aoFazerParseComSucessoDadoQueSejaInformadoTresRegistrosDeveriaRealizarParseTresRegistros() {
        configurarMocksComSucesso();
        arquivoParse.parse(umArquivoComTresRegistros());
        Mockito.verify(registroParseMock, atLeast(3)).parse(any(String[].class));
    }

    @Test
    public void aoFazerParseComSucessoDeveriaRetornarOsRegistrosEsperados() {
        configurarMocksComSucesso();
        final List<Registro> registrosRetornados = arquivoParse.parse(umArquivoQualquer());
        assertEquals(asList(registro), registrosRetornados);
    }

    @Test(expected = ArquivoInvalidoException.class)
    public void aoFazerParseComFalhaDeveriaLancarUmaArquivoInvalidoException() {
        configurarMocksComFalha();
        final List<Registro> registrosRetornados = arquivoParse.parse(umArquivoQualquer());
        assertEquals(asList(registro), registrosRetornados);
    }

    @Test
    public void aoFazerParseComFalhaDeveriaSetarNaExcecaoOhArquivoPathEsperado() {
        try {
            configurarMocksComFalha();
            arquivoParse.parse(umArquivoQualquer());
            fail("Deveria lançar uma exceção...");
        } catch (ArquivoInvalidoException excecao) {
            assertEquals(ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT, excecao.getArquivoPath());
        }
    }

    private void configurarMocksComSucesso() {
        Mockito.when(registroParseFactoryMock.obter(any(String[].class))).thenReturn(registroParseMock);
        Mockito.when(registroParseMock.parse(any(String[].class))).thenReturn(registro);
    }

    private void configurarMocksComFalha() {
        Mockito.when(registroParseFactoryMock.obter(any(String[].class))).thenReturn(registroParseMock);
        Mockito.when(registroParseMock.parse(any(String[].class))).thenThrow(new ArquivoInvalidoException("Erro de teste"));
    }
}