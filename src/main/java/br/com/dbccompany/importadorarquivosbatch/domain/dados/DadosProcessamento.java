package br.com.dbccompany.importadorarquivosbatch.domain.dados;

import java.nio.file.Path;

public class DadosProcessamento {

    private Path arquivoPath;
    private Long quantidadeClientes;
    private Long quantidadeVendedores;
    private String idVendaMaisCara;
    private String nomePiorVendedor;

    public Path getArquivoPath() {
        return arquivoPath;
    }

    public void setArquivoPath(Path arquivoPath) {
        this.arquivoPath = arquivoPath;
    }

    public Long getQuantidadeClientes() {
        return quantidadeClientes;
    }

    public void setQuantidadeClientes(Long quantidadeClientes) {
        this.quantidadeClientes = quantidadeClientes;
    }

    public Long getQuantidadeVendedores() {
        return quantidadeVendedores;
    }

    public void setQuantidadeVendedores(Long quantidadeVendedores) {
        this.quantidadeVendedores = quantidadeVendedores;
    }

    public String getIdVendaMaisCara() {
        return idVendaMaisCara;
    }

    public void setIdVendaMaisCara(String idVendaMaisCara) {
        this.idVendaMaisCara = idVendaMaisCara;
    }

    public String getNomePiorVendedor() {
        return nomePiorVendedor;
    }

    public void setNomePiorVendedor(String nomePiorVendedor) {
        this.nomePiorVendedor = nomePiorVendedor;
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