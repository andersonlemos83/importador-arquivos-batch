package br.com.dbccompany.importadorarquivosbatch.service.parse.factory.impl;

import br.com.dbccompany.importadorarquivosbatch.service.parse.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.service.parse.factory.RegistroParseFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.dbccompany.importadorarquivosbatch.domain.registro.TipoRegistro.*;

@Component
public class RegistroParseFactoryImpl implements RegistroParseFactory {

    private final Map<String, RegistroParse> parses;

    public RegistroParseFactoryImpl(@Qualifier("vendedorParse") RegistroParse vendedorParse,
                                    @Qualifier("clienteParse") RegistroParse clienteParse,
                                    @Qualifier("vendaParse") RegistroParse vendaParse) {
        parses = new HashMap<>();
        parses.put(VENDEDOR.getId(), vendedorParse);
        parses.put(CLIENTE.getId(), clienteParse);
        parses.put(VENDA.getId(), vendaParse);
    }

    @Override
    public RegistroParse obter(String id) {
        return parses.get(id);
    }
}