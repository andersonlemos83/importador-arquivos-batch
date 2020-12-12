package br.com.dbccompany.importadorarquivosbatch.service.consolidador.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.fixture.RegistroFixture;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorQuantidadeClientes;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnit4.class)
public class ConsolidadorQuantidadeClientesImplTest {

    private ConsolidadorQuantidadeClientes consolidadorQuantidadeClientes;

    @Before
    public void inicializarContexto() {
        consolidadorQuantidadeClientes = new ConsolidadorQuantidadeClientesImpl();
    }

    @Test
    public void aoConsolidarDadoQueListaDeRegistrosPossua10Vendedores5Clientes20VendasDeveriaRetornar5() {
        final List<Registro> registros = RegistroFixture.umaListaDeRegistrosCom10Vendedores5Clientes20Vendas();
        final Long quantidadeClientes = consolidadorQuantidadeClientes.consolidar(registros);
        assertEquals(5l, quantidadeClientes);
    }
}