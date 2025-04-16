package br.com.dbccompany.importadorarquivosbatch.service.consolidador.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Venda;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorVendaMaisCara;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.dbccompany.importadorarquivosbatch.shared.util.ObjectMapperUtil.generateJson;
import static java.util.Comparator.comparingDouble;

@Log4j2
@Component
public class ConsolidadorVendaMaisCaraImpl implements ConsolidadorVendaMaisCara {

    @Override
    public String consolidar(List<Registro> registros) {
        log.debug("Entrando em ConsolidadorVendaMaisCaraImpl: {}", generateJson(registros));
        final String vendaMaisCara = registros
                .stream()
                .filter(Registro::ehVenda)
                .map(registro -> (Venda) registro)
                .max(comparingDouble(Venda::obterTotal))
                .map(Venda::getIdVenda)
                .orElse("");
        log.debug("Saindo de ConsolidadorVendaMaisCaraImpl: {}", vendaMaisCara);
        return vendaMaisCara;
    }
}