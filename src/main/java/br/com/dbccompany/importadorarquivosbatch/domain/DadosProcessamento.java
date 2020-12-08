package br.com.dbccompany.importadorarquivosbatch.domain;

import java.nio.file.Path;

public class DadosProcessamento {

    private final Path arquivoPath;
    private final Long quantidadeClientes;
    private final Long quantidadeVendedores;
    private final String idVendaMaisCara;
    private final String nomePiorVendedor;

    public DadosProcessamento(Path arquivoPath, Long quantidadeClientes, Long quantidadeVendedores,
                              String idVendaMaisCara, String nomePiorVendedor) {
        this.arquivoPath = arquivoPath;
        this.quantidadeClientes = quantidadeClientes;
        this.quantidadeVendedores = quantidadeVendedores;
        this.idVendaMaisCara = idVendaMaisCara;
        this.nomePiorVendedor = nomePiorVendedor;
    }

    public Path getArquivoPath() {
        return arquivoPath;
    }

    public Long getQuantidadeClientes() {
        return quantidadeClientes;
    }

    public Long getQuantidadeVendedores() {
        return quantidadeVendedores;
    }

    public String getIdVendaMaisCara() {
        return idVendaMaisCara;
    }

    public String getNomePiorVendedor() {
        return nomePiorVendedor;
    }

    @Override
    public String toString() {
        return "DadosProcessamento{" +
                "arquivoPath=" + arquivoPath +
                ", quantidadeClientes=" + quantidadeClientes +
                ", quantidadeVendedores=" + quantidadeVendedores +
                ", idVendaMaisCara='" + idVendaMaisCara + '\'' +
                ", nomePiorVendedor='" + nomePiorVendedor + '\'' +
                '}';
    }
}