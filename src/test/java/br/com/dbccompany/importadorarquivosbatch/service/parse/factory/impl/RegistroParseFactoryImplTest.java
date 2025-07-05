package br.com.dbccompany.importadorarquivosbatch.service.parse.factory.impl;

import br.com.dbccompany.importadorarquivosbatch.service.parse.factory.RegistroParseFactory;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl.ClienteParse;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl.ItemParse;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl.VendaParse;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl.VendedorParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroSemLayoutDefinidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class RegistroParseFactoryImplTest {

    private RegistroParseFactory registroParseFactory;

    @BeforeEach
    public void inicializarContexto() {
        registroParseFactory = new RegistroParseFactoryImpl(new VendedorParse(), new ClienteParse(), new VendaParse(new ItemParse()));
    }

    @Test
    public void aoObterDadoQueSejaInformadoUmRegistroComId001DeveriaRetornarUmVendedorParse() {
        final RegistroParse registroParse = registroParseFactory.obter(new String[]{"001"});
        assertTrue("Deveria retornar um VendedorParse", registroParse instanceof VendedorParse);
    }

    @Test
    public void aoObterDadoQueSejaInformadoUmRegistroComId002DeveriaRetornarUmClienteParse() {
        final RegistroParse registroParse = registroParseFactory.obter(new String[]{"002"});
        assertTrue("Deveria retornar um ClienteParse", registroParse instanceof ClienteParse);
    }

    @Test
    public void aoObterDadoQueSejaInformadoUmRegistroComId003DeveriaRetornarUmVendaParse() {
        final RegistroParse registroParse = registroParseFactory.obter(new String[]{"003"});
        assertTrue("Deveria retornar um VendaParse", registroParse instanceof VendaParse);
    }

    @Test
    public void aoObterDadoQueSejaInformadoUmRegistroComIdInvalidoDeveriaLancarUmaRegistroSemLayoutDefinidoException() {
        final RegistroSemLayoutDefinidoException thrown = assertThrows(RegistroSemLayoutDefinidoException.class, () -> registroParseFactory.obter(new String[]{}));
        assertEquals("O arquivo possui um registro sem layout definido: null", thrown.getMessage());
    }

    @Test
    public void aoObterDadoQueSejaInformadoUmRegistroComIdInvalidoDeveriaLancarUmaExcecaoComAhMensagemRegistroSemLayoutDefinido() {
        final RegistroSemLayoutDefinidoException thrown = assertThrows(RegistroSemLayoutDefinidoException.class, () -> registroParseFactory.obter(new String[]{"004"}));
        assertEquals("O arquivo possui um registro sem layout definido: 004", thrown.getMessage());
    }
}