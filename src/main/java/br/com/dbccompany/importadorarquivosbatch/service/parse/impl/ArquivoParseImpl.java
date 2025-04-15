package br.com.dbccompany.importadorarquivosbatch.service.parse.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.Arquivo;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.service.parse.ArquivoParse;
import br.com.dbccompany.importadorarquivosbatch.service.parse.factory.RegistroParseFactory;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.ArquivoInvalidoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor
public class ArquivoParseImpl implements ArquivoParse {

    private final RegistroParseFactory registroParseFactory;

    @Override
    public List<Registro> parse(Arquivo arquivo) {
        try {
            final List<String[]> registrosArray = arquivo.getRegistrosArray();
            return registrosArray.stream()
                    .map(this::gerarRegistroParse)
                    .collect(toList());
        } catch (ArquivoInvalidoException excecao) {
            excecao.setArquivoPath(arquivo.getArquivoPath());
            throw excecao;
        }
    }

    private Registro gerarRegistroParse(String[] registro) {
        final RegistroParse registroParse = registroParseFactory.obter(registro);
        return (Registro) registroParse.parse(registro);
    }
}