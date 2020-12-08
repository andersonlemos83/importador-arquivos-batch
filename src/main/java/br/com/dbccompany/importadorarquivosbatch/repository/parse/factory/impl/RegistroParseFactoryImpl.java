package br.com.dbccompany.importadorarquivosbatch.repository.parse.factory.impl;

import br.com.dbccompany.importadorarquivosbatch.repository.parse.factory.RegistroParseFactory;
import br.com.dbccompany.importadorarquivosbatch.repository.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.IdInvalidoException;
import br.com.dbccompany.importadorarquivosbatch.shared.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    public RegistroParse obter(String[] registro) {
        final String id = ArrayUtil.obterString(registro, 0);
        return Optional.ofNullable(parses.get(id))
                .orElseThrow(() -> new IdInvalidoException(id));
    }
}