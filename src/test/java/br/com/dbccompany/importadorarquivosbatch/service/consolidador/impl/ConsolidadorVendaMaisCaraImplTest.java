package br.com.dbccompany.importadorarquivosbatch.service.consolidador.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.helper.fixture.RegistroFixture;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorVendaMaisCara;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class ConsolidadorVendaMaisCaraImplTest {

    private ConsolidadorVendaMaisCara consolidadorVendaMaisCara;

    @BeforeEach
    public void inicializarContexto() {
        consolidadorVendaMaisCara = new ConsolidadorVendaMaisCaraImpl();
    }

    @Test
    public void aoConsolidarDadoQueListaDeRegistrosPossuaUmaVendaDe8000IhUmaVendaDe2000IhUmaVendaDe1000DeveriaRetornarOhId01DaMaiorVenda() {
        final List<Registro> registros = RegistroFixture.umaListaDeRegistrosComUmaVendaDe8000IhUmaVendaDe2000IhUmaVendaDe1000();
        final String idVendaMaisCara = consolidadorVendaMaisCara.consolidar(registros);
        assertEquals("01", idVendaMaisCara);
    }

    @Test
    public void aoConsolidarDadoQueListaDeRegistrosPossuaUmaVendaDe7000IhUmaVendaDe2000IhUmaVendaDe100DeveriaRetornarOhId06DaMaiorVenda() {
        final List<Registro> registros = RegistroFixture.umaListaDeRegistrosComUmaVendaDe7000IhUmaVendaDe2000IhUmaVendaDe100();
        final String idVendaMaisCara = consolidadorVendaMaisCara.consolidar(registros);
        assertEquals("06", idVendaMaisCara);
    }
}
