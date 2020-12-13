package br.com.dbccompany.importadorarquivosbatch.domain.registro.builder;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Item;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Venda;

import java.util.List;

public final class VendaBuilder {

    private final Venda venda = new Venda();

    public static VendaBuilder umaVenda() {
        return new VendaBuilder();
    }

    public VendaBuilder comId(String id) {
        venda.setId(id);
        return this;
    }

    public VendaBuilder comIdVenda(String idVenda) {
        venda.setIdVenda(idVenda);
        return this;
    }

    public VendaBuilder comItens(List<Item> itens) {
        venda.setItens(itens);
        return this;
    }

    public VendaBuilder comNomeVendedor(String nomeVendedor) {
        venda.setNomeVendedor(nomeVendedor);
        return this;
    }

    public Venda build() {
        return venda;
    }
}