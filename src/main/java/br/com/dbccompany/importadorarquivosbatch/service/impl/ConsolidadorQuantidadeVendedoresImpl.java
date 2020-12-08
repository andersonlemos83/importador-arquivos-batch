package br.com.dbccompany.importadorarquivosbatch.service.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.service.ConsolidadorQuantidadeVendedores;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsolidadorQuantidadeVendedoresImpl implements ConsolidadorQuantidadeVendedores {

    @Override
    public Long consolidar(List<Registro> registros) {
        return registros.stream().filter(registro -> registro.ehVendedor()).count();
    }
}