package br.com.dbccompany.importadorarquivosbatch.fixture;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.builder.DadosProcessamentoBuilder;

import static br.com.dbccompany.importadorarquivosbatch.util.ConstanteTesteUtil.ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT;

public final class DadosProcessamentoFixture {

    private DadosProcessamentoFixture() {
    }

    public static DadosProcessamento umDadosProcessamentoSucessoDbc() {
        return DadosProcessamentoBuilder.umDadosProcessamento()
                .comArquivoPath(ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT)
                .comQuantidadeClientes(2l)
                .comQuantidadeVendedores(2l)
                .comIdVendaMaisCara("10")
                .comNomePiorVendedor("Paulo")
                .build();
    }
}