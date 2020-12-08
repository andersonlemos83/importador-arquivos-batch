package br.com.dbccompany.importadorarquivosbatch.domain.registro;

import static java.lang.Boolean.TRUE;
import static java.math.BigDecimal.ZERO;

public class Item implements Registro {

    private String id;
    private Integer quantidade;
    private Double preco;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public Boolean ehItem() {
        return TRUE;
    }

    public Double obterTotal() {
        if (quantidade == null || preco == null) {
            return ZERO.doubleValue();
        }
        return quantidade * preco;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                '}';
    }
}