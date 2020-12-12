package br.com.dbccompany.importadorarquivosbatch.service.parse.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.fixture.VendaFixture;
import br.com.dbccompany.importadorarquivosbatch.service.parse.ArquivoParse;
import br.com.dbccompany.importadorarquivosbatch.service.parse.factory.RegistroParseFactory;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static br.com.dbccompany.importadorarquivosbatch.fixture.RegistroFixture.umaListaRegistrosArrayComTresRegistros;
import static br.com.dbccompany.importadorarquivosbatch.fixture.RegistroFixture.umaListaRegistrosArrayComUmRegistro;
import static java.util.Arrays.asList;
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
    public void aoFazerParseDadoQueSejaInformadoApenasUmRegistroDeveriaRealizarParseDeApenasUmRegistro() {
        configurarMocks();
        arquivoParse.parse(umaListaRegistrosArrayComUmRegistro());
        Mockito.verify(registroParseMock, atLeast(1)).parse(any(String[].class));
    }

    @Test
    public void aoFazerParseDadoQueSejaInformadoTresRegistrosDeveriaRealizarParseTresRegistros() {
        configurarMocks();
        arquivoParse.parse(umaListaRegistrosArrayComTresRegistros());
        Mockito.verify(registroParseMock, atLeast(3)).parse(any(String[].class));
    }

    @Test
    public void aoFazerParseDeveriaRetornarOsRegistrosEsperados() {
        configurarMocks();
        final List<Registro> registrosRetornados = arquivoParse.parse(umaListaRegistrosArrayComUmRegistro());
        assertEquals(asList(registro), registrosRetornados);
    }

    private void configurarMocks() {
        Mockito.when(registroParseFactoryMock.obter(any(String[].class))).thenReturn(registroParseMock);
        Mockito.when(registroParseMock.parse(any(String[].class))).thenReturn(registro);
    }
}