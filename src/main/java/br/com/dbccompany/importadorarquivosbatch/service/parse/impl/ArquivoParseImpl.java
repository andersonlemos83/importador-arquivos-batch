package br.com.dbccompany.importadorarquivosbatch.service.parse.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.Arquivo;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.service.parse.ArquivoParse;
import br.com.dbccompany.importadorarquivosbatch.service.parse.factory.RegistroParseFactory;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.ArquivoInvalidoException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.dbccompany.importadorarquivosbatch.shared.util.ObjectMapperUtil.generateJson;
import static java.util.stream.Collectors.toList;

@Log4j2
@Component
@AllArgsConstructor
public class ArquivoParseImpl implements ArquivoParse {

    private final RegistroParseFactory registroParseFactory;

    @Override
    public List<Registro> parse(Arquivo arquivo) {
        try {
            log.debug("Entrando em ArquivoParseImpl: {}", generateJson(arquivo));
            final List<String[]> registrosArray = arquivo.getRegistrosArray();
            final List<Registro> registros = registrosArray.stream()
                    .map(this::gerarRegistroParse)
                    .collect(toList());
            log.debug("Saindo de ArquivoParseImpl: {}", generateJson(registros));
            return registros;
        } catch (ArquivoInvalidoException excecao) {
            log.info("Saindo de ArquivoParseImpl: {}", excecao.getMessage());
            excecao.setArquivoPath(arquivo.getArquivoPath());
            throw excecao;
        }
    }

    private Registro gerarRegistroParse(String[] registro) {
        final RegistroParse registroParse = registroParseFactory.obter(registro);
        return (Registro) registroParse.parse(registro);
    }
}