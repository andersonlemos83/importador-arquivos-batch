package br.com.dbccompany.importadorarquivosbatch.domain.registro;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.builder.VendaBuilder;
import br.com.dbccompany.importadorarquivosbatch.helper.fixture.VendaFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class VendaTest {

    @Test
    public void aoChamarObterTotalDadoQueOhTotalDoItemUmSeja8000DeveriaRetornar8000() {
        final Venda venda = VendaFixture.umaVenda01();
        assertEquals(8000d, venda.obterTotal());
    }

    @Test
    public void aoChamarObterTotalDadoQueOhTotalDoItemUmSeja1000IhTotalDoItemDoisSeja1000DeveriaRetornar2000() {
        final Venda venda = VendaFixture.umaVenda12();
        assertEquals(2000d, venda.obterTotal());
    }

    @Test
    public void aoChamarObterTotalDadoQueNaoPossuNenhumItemDeveriaRetornarZero() {
        final Venda venda = VendaBuilder.umaVenda().comItens(emptyList()).build();
        assertEquals(0d, venda.obterTotal());
    }
}