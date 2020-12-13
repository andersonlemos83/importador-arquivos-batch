package br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Venda;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroComLayoutInvalidoException;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroComTipoDadoInvalidoException;
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

    @Test(expected = RegistroComLayoutInvalidoException.class)
    public void aoFazerParseDadoQueRegistroTenhaQuantidadeAtributosInvalidoDeveriaRetornarLancarUmaRegistroComLayoutInvalidoException() {
        registroParse.parse(umaRegistroArrayVenda10ComQuantidadeInvalida());
    }

    @Test
    public void aoFazerParseDadoQueRegistroTenhaQuantidadeAtributosInvalidoDeveriaRetornarLancarUmaExcecaoComAhMensagemRegistroIncompativelComLayout() {
        try {
            registroParse.parse(umaRegistroArrayVenda10ComQuantidadeInvalida());
            fail("Deveria lançar uma exceção...");
        } catch (RegistroComLayoutInvalidoException excecao) {
            assertEquals("O arquivo possui um registro, [003, 10, [1-10-100,2-30-2.50,3-40-3.10]], incompatível com o layout Venda.", excecao.getMessage());
        }
    }

    @Test(expected = RegistroComTipoDadoInvalidoException.class)
    public void aoFazerParseDadoQueRegistroTenhaDadosInvalidosDeveriaRetornarLancarUmaRegistroComTipoDadoInvalidoException() {
        registroParse.parse(umaRegistroArrayVenda10ComDadosInvalidos());
    }

    @Test
    public void aoFazerParseDadoQueRegistroTenhaDadosInvalidosDeveriaRetornarLancarUmaExcecaoComAhMensagemRegistroComDaodsIncompativeis() {
        try {
            registroParse.parse(umaRegistroArrayVenda10ComDadosInvalidos());
            fail("Deveria lançar uma exceção...");
        } catch (RegistroComTipoDadoInvalidoException excecao) {
            assertEquals("O arquivo possui um registro, [003, 10, [1-10-100,2-30-2.50,3-40-Inválido], Pedro], com dados incompatíveis com o layout Venda.", excecao.getMessage());
        }
    }
}