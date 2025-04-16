package br.com.dbccompany.importadorarquivosbatch.helper.fixture;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Cliente;

public final class ClienteFixture {

    private ClienteFixture() {
    }

    public static Cliente umClienteQualquer() {
        return umClienteOi();
    }

    public static Cliente umClienteOi() {
        return Cliente.builder()
                .id("002")
                .cnpj("29013251000192")
                .nome("Oi")
                .areaNegocio("Telefonia")
                .build();
    }

    public static Cliente umClienteMaceioShopping() {
        return Cliente.builder()
                .id("002")
                .cnpj("12190604000127")
                .nome("Maceió Shopping")
                .areaNegocio("Shopping Centers")
                .build();
    }

    public static Cliente umClienteLivrariaSaraiva() {
        return Cliente.builder()
                .id("002")
                .cnpj("88728906000178")
                .nome("Livraria Saraiva")
                .areaNegocio("Livros")
                .build();
    }

    public static Cliente umClienteMixpel() {
        return Cliente.builder()
                .id("002")
                .cnpj("49633757000101")
                .nome("Mixpel")
                .areaNegocio("Informática")
                .build();
    }

    public static Cliente umClienteIronStudio() {
        return Cliente.builder()
                .id("002")
                .cnpj("91412787000154")
                .nome("Iron Studio")
                .areaNegocio("Colecionáveis")
                .build();
    }
}