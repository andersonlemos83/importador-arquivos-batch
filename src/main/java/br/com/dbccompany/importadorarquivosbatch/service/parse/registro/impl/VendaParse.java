package br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Item;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Venda;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class VendaParse extends AbstractRegistroParse<Venda> implements RegistroParse<Venda> {

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
    protected Venda gerarRegistro(String[] registro) {
        return Venda.builder()
                .idLayout(registro[0])
                .idVenda(registro[1])
                .itens(gerarItens(registro[2]))
                .nomeVendedor(registro[3])
                .build();
    }

    @Override
    protected String obterNome() {
        return Venda.class.getSimpleName();
    }

    private List<Item> gerarItens(String token) {
        return obterItensToken(token)
                .stream()
                .map(itemToken -> itemToken.split(SEPARADOR_CAMPOS_ITEM))
                .map(itemRegistro -> (Item) itemParse.parse(itemRegistro))
                .toList();
    }

    private List<String> obterItensToken(String tokenItem) {
        return Arrays.asList(tokenItem.replace("[", "").replace("]", "").split(SEPARADOR_ITENS));
    }
}