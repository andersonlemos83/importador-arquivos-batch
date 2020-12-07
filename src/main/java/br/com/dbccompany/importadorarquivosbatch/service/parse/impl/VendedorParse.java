package br.com.dbccompany.importadorarquivosbatch.service.parse.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Vendedor;
import br.com.dbccompany.importadorarquivosbatch.service.parse.RegistroParse;
import org.springframework.stereotype.Component;

import static java.lang.Double.parseDouble;

@Component
public class VendedorParse implements RegistroParse {

    @Override
    public Registro parse(String[] registro) {
        Vendedor vendedor = new Vendedor();
        vendedor.setId(registro[0]);
        vendedor.setCpf(registro[1]);
        vendedor.setNome(registro[2]);
        vendedor.setSalario(parseDouble(registro[3]));
        return vendedor;
    }
}
