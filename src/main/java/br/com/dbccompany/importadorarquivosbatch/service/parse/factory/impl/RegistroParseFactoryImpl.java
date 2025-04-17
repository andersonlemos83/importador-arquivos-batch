package br.com.dbccompany.importadorarquivosbatch.service.parse.factory.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Cliente;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Venda;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Vendedor;
import br.com.dbccompany.importadorarquivosbatch.service.parse.factory.RegistroParseFactory;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroSemLayoutDefinidoException;
import br.com.dbccompany.importadorarquivosbatch.shared.util.ArrayUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static br.com.dbccompany.importadorarquivosbatch.domain.registro.TipoRegistro.*;
import static br.com.dbccompany.importadorarquivosbatch.shared.util.ObjectMapperUtil.generateJson;

@Log4j2
@Component
public class RegistroParseFactoryImpl implements RegistroParseFactory<Registro> {

    private final Map<String, RegistroParse<? extends Registro>> parses;

    public RegistroParseFactoryImpl(@Qualifier("vendedorParse") RegistroParse<Vendedor> vendedorParse,
                                    @Qualifier("clienteParse") RegistroParse<Cliente> clienteParse,
                                    @Qualifier("vendaParse") RegistroParse<Venda> vendaParse) {
        parses = new HashMap<>();
        parses.put(VENDEDOR.getId(), vendedorParse);
        parses.put(CLIENTE.getId(), clienteParse);
        parses.put(VENDA.getId(), vendaParse);
    }

    @Override
    public RegistroParse<Registro> obter(String[] registro) {
        log.debug("Entrando em RegistroParseFactoryImpl: {}", generateJson(registro));
        final String id = ArrayUtil.obterString(registro, 0);
        final RegistroParse<? extends Registro> registroParse = Optional.ofNullable(parses.get(id))
                .orElseThrow(() -> new RegistroSemLayoutDefinidoException(id));
        log.debug("Saindo de RegistroParseFactoryImpl: {}", registroParse.toString());
        return (RegistroParse<Registro>) registroParse;
    }
}