package br.com.dbccompany.importadorarquivosbatch.helper.fixture;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Vendedor;

public final class VendedorFixture {

    private VendedorFixture() {
    }

    public static Vendedor umVendedorQualquer() {
        return umVendedorMortenHarket();
    }

    public static Vendedor umVendedorMortenHarket() {
        return Vendedor.builder()
                .idLayout("001")
                .cpf("44934360000")
                .nome("Morten Harket")
                .salario(10000d)
                .build();
    }

    public static Vendedor umVendedorBryanAdams() {
        return Vendedor.builder()
                .idLayout("001")
                .cpf("89622735002")
                .nome("Bryan Adams")
                .salario(9000d)
                .build();
    }

    public static Vendedor umVendedorAgnethaFaltskog() {
        return Vendedor.builder()
                .idLayout("001")
                .cpf("01387268090")
                .nome("Agnetha Fältskog")
                .salario(8000d)
                .build();
    }

    public static Vendedor umVendedorDoloresORiordan() {
        return Vendedor.builder()
                .idLayout("001")
                .cpf("64951402076")
                .nome("Dolores O'Riordan")
                .salario(7000d)
                .build();
    }

    public static Vendedor umVendedorSimonLeBon() {
        return Vendedor.builder()
                .idLayout("001")
                .cpf("35341411056")
                .nome("Simon Le Bon")
                .salario(6000d)
                .build();
    }

    public static Vendedor umVendedorJonBonJovi() {
        return Vendedor.builder()
                .idLayout("001")
                .cpf("37984192057")
                .nome("Jon Bon Jovi")
                .salario(5000d)
                .build();
    }

    public static Vendedor umVendedorRussellHitchcock() {
        return Vendedor.builder()
                .idLayout("001")
                .cpf("61022326074")
                .nome("Russell Hitchcock")
                .salario(4000d)
                .build();
    }

    public static Vendedor umVendedorCyndiLauper() {
        return Vendedor.builder()
                .idLayout("001")
                .cpf("1220372609")
                .nome("Cyndi Lauper")
                .salario(3000d)
                .build();
    }

    public static Vendedor umVendedorLauraPausini() {
        return Vendedor.builder()
                .idLayout("001")
                .cpf("08699056001")
                .nome("Laura Pausini")
                .salario(2000d)
                .build();
    }

    public static Vendedor umVendedorKlausMeine() {
        return Vendedor.builder()
                .idLayout("001")
                .cpf("78186436057")
                .nome("Klaus Meine")
                .salario(1000d)
                .build();
    }
}