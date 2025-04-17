package br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Cliente;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import org.springframework.stereotype.Component;

@Component
public class ClienteParse extends AbstractRegistroParse<Cliente> implements RegistroParse<Cliente> {

    @Override
    protected int obterQuantidadeCampos() {
        return 4;
    }

    @Override
    protected Cliente gerarRegistro(String[] registro) {
        return Cliente.builder()
                .idLayout(registro[0])
                .cnpj(registro[1])
                .nome(registro[2])
                .areaNegocio(registro[3])
                .build();
    }

    @Override
    protected String obterNome() {
        return Cliente.class.getSimpleName();
    }
}