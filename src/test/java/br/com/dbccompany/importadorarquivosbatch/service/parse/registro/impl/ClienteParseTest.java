package br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Cliente;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroComLayoutInvalidoException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static br.com.dbccompany.importadorarquivosbatch.fixture.RegistroFixture.umaRegistroArrayOi;
import static br.com.dbccompany.importadorarquivosbatch.fixture.RegistroFixture.umaRegistroArrayOiComQuantidadeInvalida;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class ClienteParseTest {

    private RegistroParse registroParse;

    @Before
    public void inicializarContexto() {
        registroParse = new ClienteParse();
    }

    @Test
    public void aoFazerParseDadoQueRegistroSejaValidoDeveriaRetornarOhClienteEsperado() {
        final Cliente cliente = (Cliente) registroParse.parse(umaRegistroArrayOi());
        assertEquals("002", cliente.getId());
        assertEquals("29013251000192", cliente.getCnpj());
        assertEquals("Oi", cliente.getNome());
        assertEquals("Telefonia", cliente.getAreaNegocio());
    }

    @Test(expected = RegistroComLayoutInvalidoException.class)
    public void aoFazerParseDadoQueRegistroTenhaQuantidadeAtributosInvalidoDeveriaRetornarLancarUmaRegistroComLayoutInvalidoException() {
        registroParse.parse(umaRegistroArrayOiComQuantidadeInvalida());
    }

    @Test
    public void aoFazerParseDadoQueRegistroTenhaQuantidadeAtributosInvalidoDeveriaRetornarLancarUmaExcecaoComAhMensagemRegistroIncompativelComLayout() {
        try {
            registroParse.parse(umaRegistroArrayOiComQuantidadeInvalida());
            fail("Deveria lançar uma exceção...");
        } catch (RegistroComLayoutInvalidoException excecao) {
            assertEquals("O arquivo possui um registro, [002, 29013251000192, Oi], incompatível com o layout Cliente.", excecao.getMessage());
        }
    }
}