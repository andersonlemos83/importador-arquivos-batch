package br.com.dbccompany.importadorarquivosbatch.repository.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Item;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.builder.ItemBuilder;
import br.com.dbccompany.importadorarquivosbatch.repository.parse.registro.RegistroParse;
import org.springframework.stereotype.Component;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

@Component
public class ItemParse extends AbstractRegistroParse<Item> implements RegistroParse<Item> {

    @Override
    protected int obterQuantidadeCampos() {
        return 3;
    }

    @Override
    protected Item gerarRegistro(String[] registro) {
        return ItemBuilder.umItem()
                .comId(registro[0])
                .comQuantidade(parseInt(registro[1]))
                .comPreco(parseDouble(registro[2]))
                .build();
    }

    @Override
    protected String obterNome() {
        return Item.class.getSimpleName();
    }
}