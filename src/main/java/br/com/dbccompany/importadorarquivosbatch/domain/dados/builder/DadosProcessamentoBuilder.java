package br.com.dbccompany.importadorarquivosbatch.domain.dados.builder;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;

import java.nio.file.Path;

public final class DadosProcessamentoBuilder {

    private final DadosProcessamento dadosProcessamento = new DadosProcessamento();

    public static DadosProcessamentoBuilder umDadosProcessamento() {
        return new DadosProcessamentoBuilder();
    }

    public DadosProcessamentoBuilder comArquivoPath(Path arquivoPath) {
        dadosProcessamento.setArquivoPath(arquivoPath);
        return this;
    }

    public DadosProcessamentoBuilder comQuantidadeClientes(Long quantidadeClientes) {
        dadosProcessamento.setQuantidadeClientes(quantidadeClientes);
        return this;
    }

    public DadosProcessamentoBuilder comQuantidadeVendedores(Long quantidadeVendedores) {
        dadosProcessamento.setQuantidadeVendedores(quantidadeVendedores);
        return this;
    }

    public DadosProcessamentoBuilder comIdVendaMaisCara(String idVendaMaisCara) {
        dadosProcessamento.setIdVendaMaisCara(idVendaMaisCara);
        return this;
    }

    public DadosProcessamentoBuilder comNomePiorVendedor(String nomePiorVendedor) {
        dadosProcessamento.setNomePiorVendedor(nomePiorVendedor);
        return this;
    }

    public DadosProcessamento build() {
        return dadosProcessamento;
    }
}
