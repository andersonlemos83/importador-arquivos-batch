package br.com.dbccompany.importadorarquivosbatch.service.parse.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.service.parse.ArquivoParse;
import br.com.dbccompany.importadorarquivosbatch.service.parse.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.service.parse.factory.RegistroParseFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArquivoParseImpl implements ArquivoParse {

    private final RegistroParseFactory registroParseFactory;

    public ArquivoParseImpl(RegistroParseFactory registroParseFactory) {
        this.registroParseFactory = registroParseFactory;
    }

    @Override
    public List<Registro> parse(List<String[]> registrosArray) {
        List<Registro> registros = new ArrayList<>();
        for (String[] registroArray : registrosArray) {
            final RegistroParse registroParse = registroParseFactory.obter(registroArray[0]);
            Registro registro = registroParse.parse(registroArray);
            registros.add(registro);
        }
        return registros;
    }
}