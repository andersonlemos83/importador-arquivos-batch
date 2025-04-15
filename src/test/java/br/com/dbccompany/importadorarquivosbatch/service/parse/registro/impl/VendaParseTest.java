package br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Venda;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroComLayoutInvalidoException;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroComTipoDadoInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.dbccompany.importadorarquivosbatch.helper.fixture.RegistroFixture.*;
import static br.com.dbccompany.importadorarquivosbatch.helper.fixture.VendaFixture.umaVenda10Pedro;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class VendaParseTest {

    private RegistroParse registroParse;

    @BeforeEach
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

    @Test
    public void aoFazerParseDadoQueRegistroTenhaQuantidadeAtributosInvalidoDeveriaRetornarLancarUmaRegistroComLayoutInvalidoException() {
        RegistroComLayoutInvalidoException thrown = assertThrows(RegistroComLayoutInvalidoException.class,
                () -> registroParse.parse(umaRegistroArrayVenda10ComQuantidadeInvalida()));
        assertEquals("O arquivo possui um registro, [003, 10, [1-10-100,2-30-2.50,3-40-3.10]], incompatível com o layout Venda.", thrown.getMessage());
    }

    @Test
    public void aoFazerParseDadoQueRegistroTenhaDadosInvalidosDeveriaRetornarLancarUmaRegistroComTipoDadoInvalidoException() {
        RegistroComTipoDadoInvalidoException thrown = assertThrows(RegistroComTipoDadoInvalidoException.class,
                () -> registroParse.parse(umaRegistroArrayVenda10ComDadosInvalidos()));
        assertEquals("O arquivo possui um registro, [003, 10, [1-10-100,2-30-2.50,3-40-Inválido], Pedro], com dados incompatíveis com o layout Venda.", thrown.getMessage());
    }
}