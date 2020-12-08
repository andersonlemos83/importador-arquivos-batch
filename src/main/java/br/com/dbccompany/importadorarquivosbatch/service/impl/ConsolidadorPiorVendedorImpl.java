package br.com.dbccompany.importadorarquivosbatch.service.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Venda;
import br.com.dbccompany.importadorarquivosbatch.service.ConsolidadorPiorVendedor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparingDouble;

@Component
public class ConsolidadorPiorVendedorImpl implements ConsolidadorPiorVendedor {

    @Override
    public String consolidar(List<Registro> registros) {
        final Optional<Venda> vendaOptional = registros
                .stream()
                .filter(Registro::ehVenda)
                .map(registro -> (Venda) registro)
                .sorted(comparingDouble(Venda::obterTotal))
                .findFirst();

        if (!vendaOptional.isPresent()) {
            return null;
        }
        return vendaOptional.get().getNomeVendedor();
    }
}