package br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Item;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
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
        return Item.builder()
                .id(registro[0])
                .quantidade(parseInt(registro[1]))
                .preco(parseDouble(registro[2]))
                .build();
    }

    @Override
    protected String obterNome() {
        return Item.class.getSimpleName();
    }
}