package br.com.dbccompany.importadorarquivosbatch.domain.registro;

import java.util.List;

import static java.lang.Boolean.TRUE;
import static java.math.BigDecimal.ZERO;

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
    public Boolean ehVenda() {
        return TRUE;
    }

    public Double obterTotal() {
        return itens.stream().map(Item::obterTotal).reduce(ZERO.doubleValue(), Double::sum);
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