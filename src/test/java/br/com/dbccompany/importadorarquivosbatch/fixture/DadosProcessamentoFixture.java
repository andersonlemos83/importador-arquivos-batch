package br.com.dbccompany.importadorarquivosbatch.fixture;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.builder.DadosProcessamentoBuilder;

import java.nio.file.Paths;

public final class DadosProcessamentoFixture {

    private DadosProcessamentoFixture() {
    }

    public static DadosProcessamento umDadosProcessamentoSucessoDbc() {
        return DadosProcessamentoBuilder.umDadosProcessamento()
                .comArquivoPath(Paths.get("sucesso-dbc.dat"))
                .comQuantidadeClientes(2l)
                .comQuantidadeVendedores(2l)
                .comIdVendaMaisCara("10")
                .comNomePiorVendedor("Paulo")
                .build();
    }
}