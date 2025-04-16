package br.com.dbccompany.importadorarquivosbatch.service.consolidador.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorQuantidadeVendedores;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.dbccompany.importadorarquivosbatch.shared.util.ObjectMapperUtil.generateJson;

@Log4j2
@Component
public class ConsolidadorQuantidadeVendedoresImpl implements ConsolidadorQuantidadeVendedores {

    @Override
    public Long consolidar(List<Registro> registros) {
        log.debug("Entrando em ConsolidadorQuantidadeVendedoresImpl: {}", generateJson(registros));
        final long quantidadeVendedores = registros.stream().filter(Registro::ehVendedor).count();
        log.debug("Saindo de ConsolidadorQuantidadeVendedoresImpl: {}", quantidadeVendedores);
        return quantidadeVendedores;
    }
}