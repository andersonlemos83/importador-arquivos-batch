package br.com.dbccompany.importadorarquivosbatch.service.consolidador.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Venda;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorVendaMaisCara;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Comparator.comparingDouble;

@Component
public class ConsolidadorVendaMaisCaraImpl implements ConsolidadorVendaMaisCara {

    @Override
    public String consolidar(List<Registro> registros) {
        return registros
                .stream()
                .filter(Registro::ehVenda)
                .map(registro -> (Venda) registro)
                .max(comparingDouble(Venda::obterTotal))
                .map(Venda::getIdVenda)
                .orElse("");
    }
}