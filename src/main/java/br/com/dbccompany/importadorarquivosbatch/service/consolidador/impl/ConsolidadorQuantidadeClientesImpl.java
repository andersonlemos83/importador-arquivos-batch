package br.com.dbccompany.importadorarquivosbatch.service.consolidador.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorQuantidadeClientes;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsolidadorQuantidadeClientesImpl implements ConsolidadorQuantidadeClientes {

    @Override
    public Long consolidar(List<Registro> registros) {
        return registros.stream().filter(Registro::ehCliente).count();
    }
}