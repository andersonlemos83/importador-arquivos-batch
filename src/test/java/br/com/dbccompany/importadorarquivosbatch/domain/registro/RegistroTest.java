package br.com.dbccompany.importadorarquivosbatch.domain.registro;

import br.com.dbccompany.importadorarquivosbatch.helper.fixture.ClienteFixture;
import br.com.dbccompany.importadorarquivosbatch.helper.fixture.VendaFixture;
import br.com.dbccompany.importadorarquivosbatch.helper.fixture.VendedorFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
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