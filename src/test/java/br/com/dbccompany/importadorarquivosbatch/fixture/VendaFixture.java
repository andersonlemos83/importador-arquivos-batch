package br.com.dbccompany.importadorarquivosbatch.fixture;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Venda;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.builder.VendaBuilder;

import static br.com.dbccompany.importadorarquivosbatch.fixture.ItemFixture.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public final class VendaFixture {

    private VendaFixture() {
    }

    public static Venda umaVendaQualquer() {
        return umaVenda01();
    }

    public static Venda umaVenda01() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("01")
                .comItens(singletonList(umItemId1Quantidade8Preco1000()))
                .comNomeVendedor("Morten Harket")
                .build();
    }

    public static Venda umaVenda02() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("02")
                .comItens(singletonList(umItemId1Quantidade2Preco1000()))
                .comNomeVendedor("Morten Harket")
                .build();
    }

    public static Venda umaVenda03() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("03")
                .comItens(asList(umItemId1Quantidade10Preco100(), umItemId2Quantidade10Preco100(),
                        umItemId3Quantidade10Preco100(), umItemId4Quantidade10Preco100(), umItemId5Quantidade10Preco100()))
                .comNomeVendedor("Bryan Adams")
                .build();
    }

    public static Venda umaVenda04() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("04")
                .comItens(singletonList(umItemId1Quantidade1Preco4000()))
                .comNomeVendedor("Bryan Adams")
                .build();
    }

    public static Venda umaVenda05() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("05")
                .comItens(singletonList(umItemId1Quantidade1Preco8000()))
                .comNomeVendedor("Agnetha Fältskog")
                .build();
    }

    public static Venda umaVenda06() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("06")
                .comItens(asList(umItemId1Quantidade1Preco1000(), umItemId2Quantidade1Preco1000(), umItemId3Quantidade1Preco1000(),
                        umItemId4Quantidade1Preco1000(), umItemId5Quantidade1Preco1000(), umItemId6Quantidade1Preco1000(),
                        umItemId7Quantidade1Preco1000()))
                .comNomeVendedor("Dolores O'Riordan")
                .build();
    }

    public static Venda umaVenda07() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("07")
                .comItens(singletonList(umItemId1Quantidade10Preco200()))
                .comNomeVendedor("Simon Le Bon")
                .build();
    }

    public static Venda umaVenda08() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("08")
                .comItens(singletonList(umItemId1Quantidade10Preco200()))
                .comNomeVendedor("Simon Le Bon")
                .build();
    }

    public static Venda umaVenda09() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("09")
                .comItens(singletonList(umItemId1Quantidade10Preco100()))
                .comNomeVendedor("Simon Le Bon")
                .build();
    }

    public static Venda umaVenda10() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("10")
                .comItens(singletonList(umItemId1Quantidade10Preco100()))
                .comNomeVendedor("Simon Le Bon")
                .build();
    }

    public static Venda umaVenda11() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("11")
                .comItens(asList(umItemId1Quantidade1000Preco1(), umItemId2Quantidade1000Preco1(),
                        umItemId3Quantidade1000Preco1(), umItemId4Quantidade1000Preco1(), umItemId5Quantidade1000Preco1()))
                .comNomeVendedor("Jon Bon Jovi")
                .build();
    }

    public static Venda umaVenda12() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("12")
                .comItens(asList(umItemId1Quantidade1000Preco1(), umItemId2Quantidade1000Preco1()))
                .comNomeVendedor("Russell Hitchcock")
                .build();
    }

    public static Venda umaVenda13() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("13")
                .comItens(singletonList(umItemId1Quantidade1000Preco1()))
                .comNomeVendedor("Russell Hitchcock")
                .build();
    }

    public static Venda umaVenda14() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("14")
                .comItens(singletonList(umItemId1Quantidade1000Preco1()))
                .comNomeVendedor("Russell Hitchcock")
                .build();
    }

    public static Venda umaVenda15() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("15")
                .comItens(asList(umItemId1Quantidade10Preco10(), umItemId2Quantidade10Preco10(), umItemId3Quantidade10Preco10(),
                        umItemId4Quantidade10Preco10(), umItemId5Quantidade10Preco10(), umItemId6Quantidade10Preco10(),
                        umItemId7Quantidade10Preco10(), umItemId8Quantidade10Preco10(), umItemId9Quantidade10Preco10(),
                        umItemId10Quantidade10Preco10()))
                .comNomeVendedor("Cyndi Lauper")
                .build();
    }

    public static Venda umaVenda16() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("16")
                .comItens(singletonList(umItemId1Quantidade4Preco250()))
                .comNomeVendedor("Cyndi Lauper")
                .build();
    }

    public static Venda umaVenda17() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("17")
                .comItens(singletonList(umItemId1Quantidade6Preco100()))
                .comNomeVendedor("Cyndi Lauper")
                .build();
    }

    public static Venda umaVenda18() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("18")
                .comItens(singletonList(umItemId1Quantidade1Preco200()))
                .comNomeVendedor("Cyndi Lauper")
                .build();
    }

    public static Venda umaVenda19() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("19")
                .comItens(singletonList(umItemId1Quantidade10Preco10()))
                .comNomeVendedor("Cyndi Lauper")
                .build();
    }

    public static Venda umaVenda20() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("20")
                .comItens(singletonList(umItemId1Quantidade100Preco1()))
                .comNomeVendedor("Cyndi Lauper")
                .build();
    }

    public static Venda umaVenda10Pedro() {
        return VendaBuilder.umaVenda()
                .comId("003")
                .comIdVenda("10")
                .comItens(asList(umItemId1Quantidade10Preco100(), umItemId2Quantidade30Preco2v50(),
                        umItemI3Quantidade40Preco3v10()))
                .comNomeVendedor("Pedro")
                .build();
    }
}