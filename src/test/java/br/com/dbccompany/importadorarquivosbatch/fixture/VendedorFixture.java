package br.com.dbccompany.importadorarquivosbatch.fixture;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Vendedor;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.builder.VendedorBuilder;

public final class VendedorFixture {

    private VendedorFixture() {
    }

    public static Vendedor umVendedorQualquer() {
        return umVendedorMortenHarket();
    }

    public static Vendedor umVendedorMortenHarket() {
        return VendedorBuilder.umVendedor()
                .comId("001")
                .comCpf("44934360000")
                .comNome("Morten Harket")
                .comSalario(10000d)
                .build();
    }

    public static Vendedor umVendedorBryanAdams() {
        return VendedorBuilder.umVendedor()
                .comId("001")
                .comCpf("89622735002")
                .comNome("Bryan Adams")
                .comSalario(9000d)
                .build();
    }

    public static Vendedor umVendedorAgnethaFaltskog() {
        return VendedorBuilder.umVendedor()
                .comId("001")
                .comCpf("01387268090")
                .comNome("Agnetha Fältskog")
                .comSalario(8000d)
                .build();
    }

    public static Vendedor umVendedorDoloresORiordan() {
        return VendedorBuilder.umVendedor()
                .comId("001")
                .comCpf("64951402076")
                .comNome("Dolores O'Riordan")
                .comSalario(7000d)
                .build();
    }

    public static Vendedor umVendedorSimonLeBon() {
        return VendedorBuilder.umVendedor()
                .comId("001")
                .comCpf("35341411056")
                .comNome("Simon Le Bon")
                .comSalario(6000d)
                .build();
    }

    public static Vendedor umVendedorJonBonJovi() {
        return VendedorBuilder.umVendedor()
                .comId("001")
                .comCpf("37984192057")
                .comNome("Jon Bon Jovi")
                .comSalario(5000d)
                .build();
    }

    public static Vendedor umVendedorRussellHitchcock() {
        return VendedorBuilder.umVendedor()
                .comId("001")
                .comCpf("61022326074")
                .comNome("Russell Hitchcock")
                .comSalario(4000d)
                .build();
    }

    public static Vendedor umVendedorCyndiLauper() {
        return VendedorBuilder.umVendedor()
                .comId("001")
                .comCpf("1220372609")
                .comNome("Cyndi Lauper")
                .comSalario(3000d)
                .build();
    }

    public static Vendedor umVendedorLauraPausini() {
        return VendedorBuilder.umVendedor()
                .comId("001")
                .comCpf("08699056001")
                .comNome("Laura Pausini")
                .comSalario(2000d)
                .build();
    }

    public static Vendedor umVendedorKlausMeine() {
        return VendedorBuilder.umVendedor()
                .comId("001")
                .comCpf("78186436057")
                .comNome("Klaus Meine")
                .comSalario(1000d)
                .build();
    }
}