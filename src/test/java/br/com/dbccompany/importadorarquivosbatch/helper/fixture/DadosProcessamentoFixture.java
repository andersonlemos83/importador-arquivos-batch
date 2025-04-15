package br.com.dbccompany.importadorarquivosbatch.helper.fixture;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.builder.DadosProcessamentoBuilder;

import static br.com.dbccompany.importadorarquivosbatch.helper.util.ConstanteUtil.ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT;

public final class DadosProcessamentoFixture {

    private DadosProcessamentoFixture() {
    }

    public static DadosProcessamento umDadosProcessamentoQualquer() {
        return umDadosProcessamentoSucessoDbc();
    }

    public static DadosProcessamento umDadosProcessamentoSucessoDbc() {
        return DadosProcessamentoBuilder.umDadosProcessamento()
                .comArquivoPath(ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT)
                .comQuantidadeClientes(2L)
                .comQuantidadeVendedores(2L)
                .comIdVendaMaisCara("10")
                .comNomePiorVendedor("Paulo")
                .build();
    }
}