package br.com.dbccompany.importadorarquivosbatch.domain.registro;

import br.com.dbccompany.importadorarquivosbatch.helper.fixture.ItemFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class ItemTest {

    @Test
    public void aoObterTotalDadoQueQuantidadeSeja1IhPrecoSeja200DeveriaRetornar200() {
        final Item item = ItemFixture.umItemId1Quantidade1Preco200();
        assertEquals(200d, item.obterTotal());
    }

    @Test
    public void aoObterTotalDadoQueQuantidadeSeja8IhPrecoSeja1000DeveriaRetornar8000() {
        final Item item = ItemFixture.umItemId1Quantidade8Preco1000();
        assertEquals(8000d, item.obterTotal());
    }

    @Test
    public void aoObterTotalDadoQueQuantidadeSejaNulaIhPrecoSeja100DeveriaRetornarZero() {
        final Item item = Item.builder().preco(100d).build();
        assertEquals(0d, item.obterTotal());
    }

    @Test
    public void aoObterTotalDadoQueQuantidadeSeja10IhPrecoSejaNulaDeveriaRetornarZero() {
        final Item item = Item.builder().quantidade(10).build();
        assertEquals(0d, item.obterTotal());
    }
}