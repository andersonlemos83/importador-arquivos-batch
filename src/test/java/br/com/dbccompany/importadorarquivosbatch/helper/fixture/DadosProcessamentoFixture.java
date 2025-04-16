package br.com.dbccompany.importadorarquivosbatch.helper.fixture;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;

import static br.com.dbccompany.importadorarquivosbatch.helper.util.ConstanteUtil.ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT;

public final class DadosProcessamentoFixture {

    private DadosProcessamentoFixture() {
    }

    public static DadosProcessamento umDadosProcessamentoQualquer() {
        return umDadosProcessamentoSucessoDbc();
    }

    public static DadosProcessamento umDadosProcessamentoSucessoDbc() {
        return DadosProcessamento.builder()
                .arquivoPath(ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT)
                .quantidadeClientes(2L)
                .quantidadeVendedores(2L)
                .idVendaMaisCara("10")
                .nomePiorVendedor("Paulo")
                .build();
    }
}