package br.com.dbccompany.importadorarquivosbatch.service.consolidador.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.helper.fixture.RegistroFixture;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorPiorVendedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class ConsolidadorPiorVendedorImplTest {

    private ConsolidadorPiorVendedor consolidadorPiorVendedor;

    @BeforeEach
    public void inicializarContexto() {
        consolidadorPiorVendedor = new ConsolidadorPiorVendedorImpl();
    }

    @Test
    public void aoConsolidarDadoQueListaDeRegistrosCom10Vendedores5Clientes20VendasDeveriaRetornaAhPiorVendedoraLauraPausini() {
        final List<Registro> registros = RegistroFixture.umaListaDeRegistrosCom10Vendedores5Clientes20Vendas();
        final String nomePiorVendedor = consolidadorPiorVendedor.consolidar(registros);
        assertEquals("Laura Pausini", nomePiorVendedor);
    }

    @Test
    public void aoConsolidarDadoQueListaDeRegistrosSemVendasComUmVendedorComSalario10000IhUmVendedorComSalario6000DeveriaRetornaOhPiorVendedorMortenHarket() {
        final List<Registro> registros = RegistroFixture.umaListaDeRegistrosSemVendasComUmVendedorComSalario10000IhUmVendedorComSalario6000();
        final String nomePiorVendedor = consolidadorPiorVendedor.consolidar(registros);
        assertEquals("Morten Harket", nomePiorVendedor);
    }

    @Test
    public void aoConsolidarDadoQueListaDeRegistrosComUmVendedorSemVendasIhUmVendedorComUmaVendaQualquerDeveriaRetornaOhPiorVendedorRussellHitchcock() {
        final List<Registro> registros = RegistroFixture.umaListaDeRegistrosComUmVendedorSemVendasIhUmVendedorComUmaVendaQualquer();
        final String nomePiorVendedor = consolidadorPiorVendedor.consolidar(registros);
        assertEquals("Russell Hitchcock", nomePiorVendedor);
    }

    @Test
    public void aoConsolidarDadoQueListaDeRegistrosComUmVendedorCom9000EmVendasIhUmVendedorCom7000EmVendasDeveriaRetornaOhPiorVendedorDoloresORiordan() {
        final List<Registro> registros = RegistroFixture.umaListaDeRegistrosComUmVendedorCom9000EmVendasIhUmVendedorCom7000EmVendas();
        final String nomePiorVendedor = consolidadorPiorVendedor.consolidar(registros);
        assertEquals("Dolores O'Riordan", nomePiorVendedor);
    }
}