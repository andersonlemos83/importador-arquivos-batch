package br.com.dbccompany.importadorarquivosbatch.helper.fixture;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Venda;

import static br.com.dbccompany.importadorarquivosbatch.helper.fixture.ItemFixture.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public final class VendaFixture {

    private VendaFixture() {
    }

    public static Venda umaVendaQualquer() {
        return umaVenda01();
    }

    public static Venda umaVenda01() {
        return Venda.builder()
                .id("003")
                .idVenda("01")
                .itens(singletonList(umItemId1Quantidade8Preco1000()))
                .nomeVendedor("Morten Harket")
                .build();
    }

    public static Venda umaVenda02() {
        return Venda.builder()
                .id("003")
                .idVenda("02")
                .itens(singletonList(umItemId1Quantidade2Preco1000()))
                .nomeVendedor("Morten Harket")
                .build();
    }

    public static Venda umaVenda03() {
        return Venda.builder()
                .id("003")
                .idVenda("03")
                .itens(asList(umItemId1Quantidade10Preco100(), umItemId2Quantidade10Preco100(),
                        umItemId3Quantidade10Preco100(), umItemId4Quantidade10Preco100(), umItemId5Quantidade10Preco100()))
                .nomeVendedor("Bryan Adams")
                .build();
    }

    public static Venda umaVenda04() {
        return Venda.builder()
                .id("003")
                .idVenda("04")
                .itens(singletonList(umItemId1Quantidade1Preco4000()))
                .nomeVendedor("Bryan Adams")
                .build();
    }

    public static Venda umaVenda05() {
        return Venda.builder()
                .id("003")
                .idVenda("05")
                .itens(singletonList(umItemId1Quantidade1Preco8000()))
                .nomeVendedor("Agnetha Fältskog")
                .build();
    }

    public static Venda umaVenda06() {
        return Venda.builder()
                .id("003")
                .idVenda("06")
                .itens(asList(umItemId1Quantidade1Preco1000(), umItemId2Quantidade1Preco1000(), umItemId3Quantidade1Preco1000(),
                        umItemId4Quantidade1Preco1000(), umItemId5Quantidade1Preco1000(), umItemId6Quantidade1Preco1000(),
                        umItemId7Quantidade1Preco1000()))
                .nomeVendedor("Dolores O'Riordan")
                .build();
    }

    public static Venda umaVenda07() {
        return Venda.builder()
                .id("003")
                .idVenda("07")
                .itens(singletonList(umItemId1Quantidade10Preco200()))
                .nomeVendedor("Simon Le Bon")
                .build();
    }

    public static Venda umaVenda08() {
        return Venda.builder()
                .id("003")
                .idVenda("08")
                .itens(singletonList(umItemId1Quantidade10Preco200()))
                .nomeVendedor("Simon Le Bon")
                .build();
    }

    public static Venda umaVenda09() {
        return Venda.builder()
                .id("003")
                .idVenda("09")
                .itens(singletonList(umItemId1Quantidade10Preco100()))
                .nomeVendedor("Simon Le Bon")
                .build();
    }

    public static Venda umaVenda10() {
        return Venda.builder()
                .id("003")
                .idVenda("10")
                .itens(singletonList(umItemId1Quantidade10Preco100()))
                .nomeVendedor("Simon Le Bon")
                .build();
    }

    public static Venda umaVenda11() {
        return Venda.builder()
                .id("003")
                .idVenda("11")
                .itens(asList(umItemId1Quantidade1000Preco1(), umItemId2Quantidade1000Preco1(),
                        umItemId3Quantidade1000Preco1(), umItemId4Quantidade1000Preco1(), umItemId5Quantidade1000Preco1()))
                .nomeVendedor("Jon Bon Jovi")
                .build();
    }

    public static Venda umaVenda12() {
        return Venda.builder()
                .id("003")
                .idVenda("12")
                .itens(asList(umItemId1Quantidade1000Preco1(), umItemId2Quantidade1000Preco1()))
                .nomeVendedor("Russell Hitchcock")
                .build();
    }

    public static Venda umaVenda13() {
        return Venda.builder()
                .id("003")
                .idVenda("13")
                .itens(singletonList(umItemId1Quantidade1000Preco1()))
                .nomeVendedor("Russell Hitchcock")
                .build();
    }

    public static Venda umaVenda14() {
        return Venda.builder()
                .id("003")
                .idVenda("14")
                .itens(singletonList(umItemId1Quantidade1000Preco1()))
                .nomeVendedor("Russell Hitchcock")
                .build();
    }

    public static Venda umaVenda15() {
        return Venda.builder()
                .id("003")
                .idVenda("15")
                .itens(asList(umItemId1Quantidade10Preco10(), umItemId2Quantidade10Preco10(), umItemId3Quantidade10Preco10(),
                        umItemId4Quantidade10Preco10(), umItemId5Quantidade10Preco10(), umItemId6Quantidade10Preco10(),
                        umItemId7Quantidade10Preco10(), umItemId8Quantidade10Preco10(), umItemId9Quantidade10Preco10(),
                        umItemId10Quantidade10Preco10()))
                .nomeVendedor("Cyndi Lauper")
                .build();
    }

    public static Venda umaVenda16() {
        return Venda.builder()
                .id("003")
                .idVenda("16")
                .itens(singletonList(umItemId1Quantidade4Preco250()))
                .nomeVendedor("Cyndi Lauper")
                .build();
    }

    public static Venda umaVenda17() {
        return Venda.builder()
                .id("003")
                .idVenda("17")
                .itens(singletonList(umItemId1Quantidade6Preco100()))
                .nomeVendedor("Cyndi Lauper")
                .build();
    }

    public static Venda umaVenda18() {
        return Venda.builder()
                .id("003")
                .idVenda("18")
                .itens(singletonList(umItemId1Quantidade1Preco200()))
                .nomeVendedor("Cyndi Lauper")
                .build();
    }

    public static Venda umaVenda19() {
        return Venda.builder()
                .id("003")
                .idVenda("19")
                .itens(singletonList(umItemId1Quantidade10Preco10()))
                .nomeVendedor("Cyndi Lauper")
                .build();
    }

    public static Venda umaVenda20() {
        return Venda.builder()
                .id("003")
                .idVenda("20")
                .itens(singletonList(umItemId1Quantidade100Preco1()))
                .nomeVendedor("Cyndi Lauper")
                .build();
    }

    public static Venda umaVenda10Pedro() {
        return Venda.builder()
                .id("003")
                .idVenda("10")
                .itens(asList(umItemId1Quantidade10Preco100(), umItemId2Quantidade30Preco2v50(),
                        umItemI3Quantidade40Preco3v10()))
                .nomeVendedor("Pedro")
                .build();
    }
}