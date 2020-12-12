package br.com.dbccompany.importadorarquivosbatch.domain.registro;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.builder.ItemBuilder;
import br.com.dbccompany.importadorarquivosbatch.fixture.ItemFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnit4.class)
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
        final Item item = ItemBuilder.umItem().comQuantidade(null).comPreco(100d).build();
        assertEquals(0d, item.obterTotal());
    }

    @Test
    public void aoObterTotalDadoQueQuantidadeSeja10IhPrecoSejaNulaDeveriaRetornarZero() {
        final Item item = ItemBuilder.umItem().comQuantidade(10).comPreco(null).build();
        assertEquals(0d, item.obterTotal());
    }
}