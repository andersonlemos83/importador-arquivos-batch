package br.com.dbccompany.importadorarquivosbatch.service.parse.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Item;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Venda;
import br.com.dbccompany.importadorarquivosbatch.service.parse.RegistroParse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

@Component
public class VendaParse implements RegistroParse {

    @Override
    public Registro parse(String[] registro) {
        Venda venda = new Venda();
        venda.setId(registro[0]);
        venda.setIdVenda(registro[1]);
        venda.setItens(gerarItens(registro[2]));
        venda.setNomeVendedor(registro[3]);
        return venda;
    }

    private List<Item> gerarItens(String tokenItem) {
        List<Item> itens = new ArrayList<>();
        for (String itemRegistro : obterItensRegistros(tokenItem)) {
            final String[] campos = itemRegistro.split("-");
            Item item = gerarItem(campos);
            itens.add(item);
        }
        return itens;
    }

    private String[] obterItensRegistros(String tokenItem) {
        return tokenItem.replace("[", "").replace("]", "").split(",");
    }

    private Item gerarItem(String[] campos) {
        Item item = new Item();
        item.setId(campos[0]);
        item.setQuantidade(parseInt(campos[1]));
        item.setPreco(parseDouble(campos[2]));
        return item;
    }
}
