package br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Item;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroComLayoutInvalidoException;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroComTipoDadoInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.dbccompany.importadorarquivosbatch.helper.fixture.RegistroFixture.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class ItemParseTest {

    private RegistroParse registroParse;

    @BeforeEach
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

    @Test
    public void aoFazerParseDadoQueRegistroTenhaQuantidadeAtributosInvalidoDeveriaRetornarLancarUmaRegistroComLayoutInvalidoException() {
        RegistroComLayoutInvalidoException thrown = assertThrows(RegistroComLayoutInvalidoException.class,
                () -> registroParse.parse(umaRegistroArrayItensVenda10ComQuantidadeInvalida()));
        assertEquals("O arquivo possui um registro, [1, 10], incompatível com o layout Item.", thrown.getMessage());
    }

    @Test
    public void aoFazerParseDadoQueRegistroTenhaDadosInvalidosDeveriaRetornarLancarUmaRegistroComTipoDadoInvalidoException() {
        RegistroComTipoDadoInvalidoException thrown = assertThrows(RegistroComTipoDadoInvalidoException.class,
                () -> registroParse.parse(umaRegistroArrayItensVenda10ComDadosInvalidos()));
        assertEquals("O arquivo possui um registro, [1, 10, Inválido], com dados incompatíveis com o layout Item.", thrown.getMessage());
    }
}