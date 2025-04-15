package br.com.dbccompany.importadorarquivosbatch.service.consolidador.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.helper.fixture.RegistroFixture;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorQuantidadeVendedores;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class ConsolidadorQuantidadeVendedoresImplTest {

    private ConsolidadorQuantidadeVendedores consolidadorQuantidadeVendedores;

    @BeforeEach
    public void inicializarContexto() {
        consolidadorQuantidadeVendedores = new ConsolidadorQuantidadeVendedoresImpl();
    }

    @Test
    public void aoConsolidarDadoQueListaDeRegistrosPossua10Vendedores5Clientes20VendasDeveriaRetornar10() {
        final List<Registro> registros = RegistroFixture.umaListaDeRegistrosCom10Vendedores5Clientes20Vendas();
        final Long quantidadeVendedores = consolidadorQuantidadeVendedores.consolidar(registros);
        assertEquals(10L, quantidadeVendedores);
    }
}