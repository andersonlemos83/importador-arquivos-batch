package br.com.dbccompany.importadorarquivosbatch.domain.registro;

import br.com.dbccompany.importadorarquivosbatch.fixture.ClienteFixture;
import br.com.dbccompany.importadorarquivosbatch.fixture.VendaFixture;
import br.com.dbccompany.importadorarquivosbatch.fixture.VendedorFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(JUnit4.class)
public class RegistroTest {

    @Test
    public void aoChamarMetodoEhClienteDadoQueRegistroSejaClienteDeveriaRetornarVerdadeiro() {
        final Registro registro = ClienteFixture.umClienteQualquer();
        assertTrue("Deveria retornar verdadeiro", registro.ehCliente());
    }

    @Test
    public void aoChamarMetodoEhClienteDadoQueRegistroNaoSejaClienteDeveriaRetornarFalso() {
        final Registro registro = VendaFixture.umaVendaQualquer();
        assertFalse("Deveria retornar falso", registro.ehCliente());
    }

    @Test
    public void aoChamarMetodoEhVendedorDadoQueRegistroSejaVendedorDeveriaRetornarVerdadeiro() {
        final Registro registro = VendedorFixture.umVendedorQualquer();
        assertTrue("Deveria retornar verdadeiro", registro.ehVendedor());
    }

    @Test
    public void aoChamarMetodoEhVendedorDadoQueRegistroNaoSejaVendedorDeveriaRetornarFalso() {
        final Registro registro = VendaFixture.umaVendaQualquer();
        assertFalse("Deveria retornar falso", registro.ehVendedor());
    }

    @Test
    public void aoChamarMetodoEhVendaDadoQueRegistroSejaVendaDeveriaRetornarVerdadeiro() {
        final Registro registro = VendaFixture.umaVendaQualquer();
        assertTrue("Deveria retornar verdadeiro", registro.ehVenda());
    }

    @Test
    public void aoChamarMetodoEhVendaDadoQueRegistroNaoSejaVendaDeveriaRetornarFalso() {
        final Registro registro = VendedorFixture.umVendedorQualquer();
        assertFalse("Deveria retornar falso", registro.ehVenda());
    }
}