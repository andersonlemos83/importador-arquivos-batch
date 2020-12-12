package br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Item;
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

    @Test(expected = QuantidadeAtributosInvalidoException.class)
    public void aoFazerParseDadoQueRegistroTenhaQuantidadeAtributosInvalidoDeveriaRetornarLancarUmaQuantidadeAtributosInvalidoException() {
        registroParse.parse(umaRegistroArrayItensVenda10ComQuantidadeInvalida());
    }

    @Test
    public void aoFazerParseDadoQueRegistroTenhaQuantidadeAtributosInvalidoDeveriaRetornarLancarUmaExcecaoComAhMensagemQuantidadeAtributosInvalidos() {
        try {
            registroParse.parse(umaRegistroArrayItensVenda10ComQuantidadeInvalida());
            fail("Deveria lançar uma exceção...");
        } catch (QuantidadeAtributosInvalidoException excecao) {
            assertEquals("A quantidade de atributos do registro [1, 10] é incompatível com os dados de Item.", excecao.getMessage());
        }
    }

    @Test(expected = RegistroParseException.class)
    public void aoFazerParseDadoQueRegistroTenhaDadosInvalidosDeveriaRetornarLancarUmaRegistroParseException() {
        registroParse.parse(umaRegistroArrayItensVenda10ComDadosInvalidos());
    }

    @Test
    public void aoFazerParseDadoQueRegistroTenhaDadosInvalidosDeveriaRetornarLancarUmaExcecaoComAhMensagemOcorreuUmErroInesperado() {
        try {
            registroParse.parse(umaRegistroArrayItensVenda10ComDadosInvalidos());
            fail("Deveria lançar uma exceção...");
        } catch (RegistroParseException excecao) {
            assertEquals("Ocorreu um erro inesperado durante o parse do registro [1, 10, Inválido] para Item.", excecao.getMessage());
        }
    }
}