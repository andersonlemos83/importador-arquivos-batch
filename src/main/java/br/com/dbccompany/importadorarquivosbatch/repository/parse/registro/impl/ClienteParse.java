package br.com.dbccompany.importadorarquivosbatch.repository.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Cliente;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.builder.ClienteBuilder;
import br.com.dbccompany.importadorarquivosbatch.repository.parse.registro.RegistroParse;
import org.springframework.stereotype.Component;

@Component
public class ClienteParse extends AbstractRegistroParse implements RegistroParse {

    @Override
    protected int obterQuantidadeCampos() {
        return 4;
    }

    @Override
    protected Registro gerarRegistro(String[] registro) {
        return ClienteBuilder.umCliente()
                .comId(registro[0])
                .comCnpj(registro[1])
                .comNome(registro[2])
                .comAreaNegocio(registro[3])
                .build();
    }

    @Override
    protected String obterNome() {
        return Cliente.class.getName();
    }
}