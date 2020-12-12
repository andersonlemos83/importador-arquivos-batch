package br.com.dbccompany.importadorarquivosbatch.service.parse.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.Arquivo;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.service.parse.ArquivoParse;
import br.com.dbccompany.importadorarquivosbatch.service.parse.factory.RegistroParseFactory;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.ArquivoInvalidoException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Component
public class ArquivoParseImpl implements ArquivoParse {

    private final RegistroParseFactory registroParseFactory;

    public ArquivoParseImpl(RegistroParseFactory registroParseFactory) {
        this.registroParseFactory = registroParseFactory;
    }

    @Override
    public List<Registro> parse(Arquivo arquivo) {
        try {
            final List<String[]> registrosArray = arquivo.getRegistrosArray();
            return registrosArray.stream()
                    .map(gerarRegistroParseFunction())
                    .collect(toList());
        } catch (ArquivoInvalidoException excecao) {
            excecao.setArquivoPath(arquivo.getArquivoPath());
            throw excecao;
        }
    }

    private Function<String[], Registro> gerarRegistroParseFunction() {
        return registro -> {
            final RegistroParse registroParse = registroParseFactory.obter(registro);
            return (Registro) registroParse.parse(registro);
        };
    }
}