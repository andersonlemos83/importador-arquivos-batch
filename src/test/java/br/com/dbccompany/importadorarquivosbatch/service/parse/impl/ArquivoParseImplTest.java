package br.com.dbccompany.importadorarquivosbatch.service.parse.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.Arquivo;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.helper.fixture.VendaFixture;
import br.com.dbccompany.importadorarquivosbatch.service.parse.factory.RegistroParseFactory;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.ArquivoInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static br.com.dbccompany.importadorarquivosbatch.helper.fixture.ArquivoFixture.*;
import static br.com.dbccompany.importadorarquivosbatch.helper.util.ConstanteUtil.ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class ArquivoParseImplTest {

    @InjectMocks
    private ArquivoParseImpl arquivoParse;

    @Mock
    private RegistroParseFactory registroParseFactoryMock;

    @Mock
    private RegistroParse registroParseMock;

    @Test
    public void aoFazerParseComSucessoDadoQueSejaInformadoApenasUmRegistroDeveriaRealizarParseDeApenasUmRegistro() {
        Registro registro = VendaFixture.umaVendaQualquer();
        configurarMocksComSucesso(registro);
        arquivoParse.parse(umArquivoComUmRegistro());
        Mockito.verify(registroParseMock, atLeast(1)).parse(any(String[].class));
    }

    @Test
    public void aoFazerParseComSucessoDadoQueSejaInformadoTresRegistrosDeveriaRealizarParseTresRegistros() {
        Registro registro = VendaFixture.umaVendaQualquer();
        configurarMocksComSucesso(registro);
        arquivoParse.parse(umArquivoComTresRegistros());
        Mockito.verify(registroParseMock, atLeast(3)).parse(any(String[].class));
    }

    @Test
    public void aoFazerParseComSucessoDeveriaRetornarOsRegistrosEsperados() {
        Registro registro = VendaFixture.umaVendaQualquer();
        configurarMocksComSucesso(registro);
        List<Registro> registrosRetornados = arquivoParse.parse(umArquivoQualquer());
        assertEquals(singletonList(registro), registrosRetornados);
    }

    @Test
    public void aoFazerParseComFalhaDeveriaLancarUmaArquivoInvalidoExceptionComOhArquivoPathEsperado() {
        String mensagemEsperada = "Erro de teste";
        Mockito.when(registroParseFactoryMock.obter(any(String[].class))).thenReturn(registroParseMock);
        Mockito.when(registroParseMock.parse(any(String[].class))).thenThrow(new ArquivoInvalidoException(mensagemEsperada));

        Arquivo arquivo = umArquivoQualquer();
        ArquivoInvalidoException thrown = assertThrows(ArquivoInvalidoException.class, () -> arquivoParse.parse(arquivo));

        assertEquals(ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT, thrown.getArquivoPath());
        assertEquals(mensagemEsperada, thrown.getMessage());
    }

    private void configurarMocksComSucesso(Registro registro) {
        Mockito.when(registroParseFactoryMock.obter(any(String[].class))).thenReturn(registroParseMock);
        Mockito.when(registroParseMock.parse(any(String[].class))).thenReturn(registro);
    }
}