package br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Venda;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.QuantidadeAtributosInvalidoException;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static br.com.dbccompany.importadorarquivosbatch.fixture.RegistroFixture.*;
import static br.com.dbccompany.importadorarquivosbatch.fixture.VendaFixture.umaVenda10Pedro;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class VendaParseTest {

    private RegistroParse registroParse;

    @Before
    public void inicializarContexto() {
        registroParse = new VendaParse(new ItemParse());
    }

    @Test
    public void aoFazerParseDadoQueRegistroSejaValidoDeveriaRetornarOhVendaEsperado() {
        final Venda venda = (Venda) registroParse.parse(umaRegistroArrayVenda10());
        assertEquals("003", venda.getId());
        assertEquals("10", venda.getIdVenda());
        assertEquals(umaVenda10Pedro().getItens().toString(), venda.getItens().toString());
        assertEquals("Pedro", venda.getNomeVendedor());
    }

    @Test(expected = QuantidadeAtributosInvalidoException.class)
    public void aoFazerParseDadoQueRegistroTenhaQuantidadeAtributosInvalidoDeveriaRetornarLancarUmaQuantidadeAtributosInvalidoException() {
        registroParse.parse(umaRegistroArrayVenda10ComQuantidadeInvalida());
    }

    @Test
    public void aoFazerParseDadoQueRegistroTenhaQuantidadeAtributosInvalidoDeveriaRetornarLancarUmaExcecaoComAhMensagemQuantidadeAtributosInvalidos() {
        try {
            registroParse.parse(umaRegistroArrayVenda10ComQuantidadeInvalida());
            fail("Deveria lançar uma exceção...");
        } catch (QuantidadeAtributosInvalidoException excecao) {
            assertEquals("A quantidade de atributos do registro [003, 10, [1-10-100,2-30-2.50,3-40-3.10]] é incompatível com os dados de Venda.", excecao.getMessage());
        }
    }

    @Test(expected = RegistroParseException.class)
    public void aoFazerParseDadoQueRegistroTenhaDadosInvalidosDeveriaRetornarLancarUmaRegistroParseException() {
        registroParse.parse(umaRegistroArrayVenda10ComDadosInvalidos());
    }

    @Test
    public void aoFazerParseDadoQueRegistroTenhaDadosInvalidosDeveriaRetornarLancarUmaExcecaoComAhMensagemOcorreuUmErroInesperado() {
        try {
            registroParse.parse(umaRegistroArrayVenda10ComDadosInvalidos());
            fail("Deveria lançar uma exceção...");
        } catch (RegistroParseException excecao) {
            assertEquals("Ocorreu um erro inesperado durante o parse do registro [003, 10, [1-10-100,2-30-2.50,3-40-Inválido], Pedro] para Venda.", excecao.getMessage());
        }
    }
}