package br.com.dbccompany.importadorarquivosbatch.domain.registro;

public class Item {

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

    public Double obterTotal() {
        if (quantidade == null || preco == null) {
            return 0d;
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