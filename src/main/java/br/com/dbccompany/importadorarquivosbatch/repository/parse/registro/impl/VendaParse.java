package br.com.dbccompany.importadorarquivosbatch.repository.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Item;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Venda;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.builder.VendaBuilder;
import br.com.dbccompany.importadorarquivosbatch.repository.parse.registro.RegistroParse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class VendaParse extends AbstractRegistroParse implements RegistroParse {

    private static final String SEPARADOR_ITENS = ",";
    private static final String SEPARADOR_CAMPOS_ITEM = "-";

    private final RegistroParse itemParse;

    public VendaParse(@Qualifier("itemParse") RegistroParse itemParse) {
        this.itemParse = itemParse;
    }

    @Override
    protected int obterQuantidadeCampos() {
        return 4;
    }

    @Override
    protected Registro gerarRegistro(String[] registro) {
        return VendaBuilder.umaVenda()
                .comId(registro[0])
                .comIdVenda(registro[1])
                .comItens(gerarItens(registro[2]))
                .comNomeVendedor(registro[3])
                .build();
    }

    @Override
    protected String obterNome() {
        return Venda.class.getName();
    }

    private List<Item> gerarItens(String token) {
        return obterItensToken(token)
                .stream()
                .map(itemToken -> itemToken.split(SEPARADOR_CAMPOS_ITEM))
                .map(itemRegistro -> (Item) itemParse.parse(itemRegistro))
                .collect(toList());
    }

    private List<String> obterItensToken(String tokenItem) {
        return Arrays.asList(tokenItem.replace("[", "").replace("]", "").split(SEPARADOR_ITENS));
    }
}