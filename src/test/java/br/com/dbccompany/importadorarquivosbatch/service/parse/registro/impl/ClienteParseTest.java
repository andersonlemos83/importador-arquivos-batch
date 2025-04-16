package br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Cliente;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroComLayoutInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.dbccompany.importadorarquivosbatch.helper.fixture.RegistroFixture.umaRegistroArrayOi;
import static br.com.dbccompany.importadorarquivosbatch.helper.fixture.RegistroFixture.umaRegistroArrayOiComQuantidadeInvalida;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class ClienteParseTest {

    private RegistroParse registroParse;

    @BeforeEach
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

    @Test
    public void aoFazerParseDadoQueRegistroTenhaQuantidadeAtributosInvalidoDeveriaRetornarLancarUmaRegistroComLayoutInvalidoException() {
        String[] registro = umaRegistroArrayOiComQuantidadeInvalida();
        RegistroComLayoutInvalidoException thrown = assertThrows(RegistroComLayoutInvalidoException.class, () -> registroParse.parse(registro));
        assertEquals("O arquivo possui um registro, [002, 29013251000192, Oi], incompatível com o layout Cliente.", thrown.getMessage());
    }
}