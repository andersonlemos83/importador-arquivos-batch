package br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Vendedor;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroComLayoutInvalidoException;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroComTipoDadoInvalidoException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

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

    @Test(expected = RegistroComLayoutInvalidoException.class)
    public void aoFazerParseDadoQueRegistroTenhaQuantidadeAtributosInvalidoDeveriaRetornarLancarUmaRegistroComLayoutInvalidoException() {
        registroParse.parse(umaRegistroArrayPedroComQuantidadeInvalida());
    }

    @Test
    public void aoFazerParseDadoQueRegistroTenhaQuantidadeAtributosInvalidoDeveriaRetornarLancarUmaExcecaoComAhMensagemRegistroIncompativelComLayout() {
        try {
            registroParse.parse(umaRegistroArrayPedroComQuantidadeInvalida());
            fail("Deveria lançar uma exceção...");
        } catch (RegistroComLayoutInvalidoException excecao) {
            assertEquals("O arquivo possui um registro, [001, 1234567891234, Pedro], incompatível com o layout Vendedor.", excecao.getMessage());
        }
    }

    @Test(expected = RegistroComTipoDadoInvalidoException.class)
    public void aoFazerParseDadoQueRegistroTenhaDadosInvalidosDeveriaRetornarLancarUmaRegistroComTipoDadoInvalidoException() {
        registroParse.parse(umaRegistroArrayPedroComDadosInvalidos());
    }

    @Test
    public void aoFazerParseDadoQueRegistroTenhaDadosInvalidosDeveriaRetornarLancarUmaExcecaoComAhMensagemRegistroComDaodsIncompativeis() {
        try {
            registroParse.parse(umaRegistroArrayPedroComDadosInvalidos());
            fail("Deveria lançar uma exceção...");
        } catch (RegistroComTipoDadoInvalidoException excecao) {
            assertEquals("O arquivo possui um registro, [001, 1234567891234, Pedro, Inválido], com dados incompatíveis com o layout Vendedor.", excecao.getMessage());
        }
    }
}