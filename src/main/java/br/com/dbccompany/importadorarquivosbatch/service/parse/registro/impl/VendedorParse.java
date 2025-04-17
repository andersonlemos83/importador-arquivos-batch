package br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Vendedor;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import org.springframework.stereotype.Component;

import static java.lang.Double.parseDouble;

@Component
public class VendedorParse extends AbstractRegistroParse<Vendedor> implements RegistroParse<Vendedor> {

    @Override
    protected int obterQuantidadeCampos() {
        return 4;
    }

    @Override
    protected Vendedor gerarRegistro(String[] registro) {
        return Vendedor.builder()
                .idLayout(registro[0])
                .cpf(registro[1])
                .nome(registro[2])
                .salario(parseDouble(registro[3]))
                .build();
    }

    @Override
    protected String obterNome() {
        return Vendedor.class.getSimpleName();
    }

    @Override
    public String toString() {
        return "VendedorParse";
    }
}