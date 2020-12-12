package br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Vendedor;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.QuantidadeAtributosInvalidoException;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static br.com.dbccompany.importadorarquivosbatch.fixture.RegistroFixture.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class VendedorParseTest {

    private RegistroParse registroParse;

    @Before
    public void inicializarContexto() {
        registroParse = new VendedorParse();
    }

    @Test
    public void aoFazerParseDadoQueRegistroSejaValidoDeveriaRetornarOhVendedorEsperado() {
        final Vendedor vendedor = (Vendedor) registroParse.parse(umaRegistroArrayPedro());
        assertEquals("001", vendedor.getId());
        assertEquals("1234567891234", vendedor.getCpf());
        assertEquals("Pedro", vendedor.getNome());
        assertEquals(Double.valueOf("50000"), vendedor.getSalario());
    }

    @Test(expected = QuantidadeAtributosInvalidoException.class)
    public void aoFazerParseDadoQueRegistroTenhaQuantidadeAtributosInvalidoDeveriaRetornarLancarUmaQuantidadeAtributosInvalidoException() {
        registroParse.parse(umaRegistroArrayPedroComQuantidadeInvalida());
    }

    @Test
    public void aoFazerParseDadoQueRegistroTenhaQuantidadeAtributosInvalidoDeveriaRetornarLancarUmaExcecaoComAhMensagemQuantidadeAtributosInvalidos() {
        try {
            registroParse.parse(umaRegistroArrayPedroComQuantidadeInvalida());
            fail("Deveria lançar uma exceção...");
        } catch (QuantidadeAtributosInvalidoException excecao) {
            assertEquals("A quantidade de atributos do registro [001, 1234567891234, Pedro] é incompatível com os dados de Vendedor.", excecao.getMessage());
        }
    }

    @Test(expected = RegistroParseException.class)
    public void aoFazerParseDadoQueRegistroTenhaDadosInvalidosDeveriaRetornarLancarUmaRegistroParseException() {
        registroParse.parse(umaRegistroArrayPedroComDadosInvalidos());
    }

    @Test
    public void aoFazerParseDadoQueRegistroTenhaDadosInvalidosDeveriaRetornarLancarUmaExcecaoComAhMensagemOcorreuUmErroInesperado() {
        try {
            registroParse.parse(umaRegistroArrayPedroComDadosInvalidos());
            fail("Deveria lançar uma exceção...");
        } catch (RegistroParseException excecao) {
            assertEquals("Ocorreu um erro inesperado durante o parse do registro [001, 1234567891234, Pedro, Inválido] para Vendedor.", excecao.getMessage());
        }
    }
}