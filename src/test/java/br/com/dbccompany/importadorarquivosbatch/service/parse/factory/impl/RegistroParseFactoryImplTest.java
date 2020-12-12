package br.com.dbccompany.importadorarquivosbatch.service.parse.factory.impl;

import br.com.dbccompany.importadorarquivosbatch.service.parse.factory.RegistroParseFactory;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl.ClienteParse;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl.ItemParse;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl.VendaParse;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl.VendedorParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.IdInvalidoException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RegistroParseFactoryImplTest {

    private RegistroParseFactory registroParseFactory;

    private RegistroParse vendedorParse;
    private RegistroParse clienteParse;
    private RegistroParse vendaParse;

    @Before
    public void inicializarContexto() {
        vendedorParse = new VendedorParse();
        clienteParse = new ClienteParse();
        vendaParse = new VendaParse(new ItemParse());

        registroParseFactory = new RegistroParseFactoryImpl(vendedorParse, clienteParse, vendaParse);
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

    @Test(expected = IdInvalidoException.class)
    public void aoObterDadoQueSejaInformadoUmRegistroComIdInvalidoDeveriaLancarUmaIdInvalidoException() {
        registroParseFactory.obter(new String[]{});
    }

    @Test
    public void aoObterDadoQueSejaInformadoUmRegistroComIdInvalidoDeveriaLancarUmaExcecaoComAhMensagemIdInvalido() {
        try {
            registroParseFactory.obter(new String[]{"004"});
            fail("Deveria lançar uma exceção...");
        } catch (IdInvalidoException excecao) {
            assertEquals("O arquivo importado possui um registro com ID inválido: 004", excecao.getMessage());
        }
    }
}