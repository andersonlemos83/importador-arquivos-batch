package br.com.dbccompany.importadorarquivosbatch.domain.registro;

import java.util.List;

public class Venda implements Registro {

    private String id;
    private String idVenda;
    private List<Item> itens;
    private String nomeVendedor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(String idVenda) {
        this.idVenda = idVenda;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id='" + id + '\'' +
                ", idVenda='" + idVenda + '\'' +
                ", itens=" + itens +
                ", nomeVendedor='" + nomeVendedor + '\'' +
                '}';
    }
}