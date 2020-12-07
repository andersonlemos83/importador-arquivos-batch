package br.com.dbccompany.importadorarquivosbatch.service.parse.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Cliente;
import br.com.dbccompany.importadorarquivosbatch.service.parse.RegistroParse;
import org.springframework.stereotype.Component;

@Component
public class ClienteParse implements RegistroParse {

    @Override
    public Registro parse(String[] registro) {
        Cliente cliente = new Cliente();
        cliente.setId(registro[0]);
        cliente.setCnpj(registro[1]);
        cliente.setNome(registro[2]);
        cliente.setAreaNegocio(registro[3]);
        return cliente;
    }
}