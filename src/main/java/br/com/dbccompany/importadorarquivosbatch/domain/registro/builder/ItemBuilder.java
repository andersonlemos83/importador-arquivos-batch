package br.com.dbccompany.importadorarquivosbatch.domain.registro.builder;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Item;

public final class ItemBuilder {

    private Item item = new Item();

    public static ItemBuilder umItem() {
        return new ItemBuilder();
    }

    public ItemBuilder comId(String id) {
        item.setId(id);
        return this;
    }

    public ItemBuilder comQuantidade(Integer quantidade) {
        item.setQuantidade(quantidade);
        return this;
    }

    public ItemBuilder comPreco(Double preco) {
        item.setPreco(preco);
        return this;
    }

    public Item build() {
        return item;
    }
}
