package br.com.dbccompany.importadorarquivosbatch.service.consolidador.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorQuantidadeClientes;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.dbccompany.importadorarquivosbatch.shared.util.ObjectMapperUtil.generateJson;

@Log4j2
@Component
public class ConsolidadorQuantidadeClientesImpl implements ConsolidadorQuantidadeClientes {

    @Override
    public Long consolidar(List<Registro> registros) {
        log.debug("Entrando em ConsolidadorQuantidadeClientesImpl: {}", generateJson(registros));
        final long quantidadeClientes = registros.stream().filter(Registro::ehCliente).count();
        log.debug("Saindo de ConsolidadorQuantidadeClientesImpl: {}", quantidadeClientes);
        return quantidadeClientes;
    }
}