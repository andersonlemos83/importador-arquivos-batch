package br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Item;
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
public class ItemParseTest {

    private RegistroParse registroParse;

    @Before
    public void inicializarContexto() {
        registroParse = new ItemParse();
    }

    @Test
    public void aoFazerParseDadoQueRegistroSejaValidoDeveriaRetornarOhItemEsperado() {
        final Item item = (Item) registroParse.parse(umaRegistroArrayItensVenda10());
        assertEquals("1", item.getId());
        assertEquals(Integer.valueOf("10"), item.getQuantidade());
        assertEquals(Double.valueOf("100"), item.getPreco());
    }

    @Test(expected = RegistroComLayoutInvalidoException.class)
    public void aoFazerParseDadoQueRegistroTenhaQuantidadeAtributosInvalidoDeveriaRetornarLancarUmaRegistroComLayoutInvalidoException() {
        registroParse.parse(umaRegistroArrayItensVenda10ComQuantidadeInvalida());
    }

    @Test
    public void aoFazerParseDadoQueRegistroTenhaQuantidadeAtributosInvalidoDeveriaRetornarLancarUmaExcecaoComAhMensagemRegistroIncompativelComLayout() {
        try {
            registroParse.parse(umaRegistroArrayItensVenda10ComQuantidadeInvalida());
            fail("Deveria lançar uma exceção...");
        } catch (RegistroComLayoutInvalidoException excecao) {
            assertEquals("O arquivo possui um registro, [1, 10], incompatível com o layout Item.", excecao.getMessage());
        }
    }

    @Test(expected = RegistroComTipoDadoInvalidoException.class)
    public void aoFazerParseDadoQueRegistroTenhaDadosInvalidosDeveriaRetornarLancarUmaRegistroComTipoDadoInvalidoException() {
        registroParse.parse(umaRegistroArrayItensVenda10ComDadosInvalidos());
    }

    @Test
    public void aoFazerParseDadoQueRegistroTenhaDadosInvalidosDeveriaRetornarLancarUmaExcecaoComAhMensagemRegistroComDaodsIncompativeis() {
        try {
            registroParse.parse(umaRegistroArrayItensVenda10ComDadosInvalidos());
            fail("Deveria lançar uma exceção...");
        } catch (RegistroComTipoDadoInvalidoException excecao) {
            assertEquals("O arquivo possui um registro, [1, 10, Inválido], com dados incompatíveis com o layout Item.", excecao.getMessage());
        }
    }
}