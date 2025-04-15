package br.com.dbccompany.importadorarquivosbatch.helper.fixture;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Cliente;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.builder.ClienteBuilder;

public final class ClienteFixture {

    private ClienteFixture() {
    }

    public static Cliente umClienteQualquer() {
        return umClienteOi();
    }

    public static Cliente umClienteOi() {
        return ClienteBuilder.umCliente()
                .comId("002")
                .comCnpj("29013251000192")
                .comNome("Oi")
                .comAreaNegocio("Telefonia")
                .build();
    }

    public static Cliente umClienteMaceioShopping() {
        return ClienteBuilder.umCliente()
                .comId("002")
                .comCnpj("12190604000127")
                .comNome("Maceió Shopping")
                .comAreaNegocio("Shopping Centers")
                .build();
    }

    public static Cliente umClienteLivrariaSaraiva() {
        return ClienteBuilder.umCliente()
                .comId("002")
                .comCnpj("88728906000178")
                .comNome("Livraria Saraiva")
                .comAreaNegocio("Livros")
                .build();
    }

    public static Cliente umClienteMixpel() {
        return ClienteBuilder.umCliente()
                .comId("002")
                .comCnpj("49633757000101")
                .comNome("Mixpel")
                .comAreaNegocio("Informática")
                .build();
    }

    public static Cliente umClienteIronStudio() {
        return ClienteBuilder.umCliente()
                .comId("002")
                .comCnpj("91412787000154")
                .comNome("Iron Studio")
                .comAreaNegocio("Colecionáveis")
                .build();
    }
}