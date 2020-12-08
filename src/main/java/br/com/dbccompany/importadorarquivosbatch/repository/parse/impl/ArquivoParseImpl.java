package br.com.dbccompany.importadorarquivosbatch.repository.parse.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.repository.parse.ArquivoParse;
import br.com.dbccompany.importadorarquivosbatch.repository.parse.factory.RegistroParseFactory;
import br.com.dbccompany.importadorarquivosbatch.repository.parse.registro.RegistroParse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class ArquivoParseImpl implements ArquivoParse {

    private final RegistroParseFactory registroParseFactory;

    public ArquivoParseImpl(RegistroParseFactory registroParseFactory) {
        this.registroParseFactory = registroParseFactory;
    }

    @Override
    public List<Registro> parse(List<String[]> registrosArray) {
        return registrosArray.stream().map(gerarRegistroParseFunction()).collect(toList());
    }

    private Function<String[], Registro> gerarRegistroParseFunction() {
        return registro -> {
            final RegistroParse registroParse = registroParseFactory.obter(registro);
            return registroParse.parse(registro);
        };
    }
}