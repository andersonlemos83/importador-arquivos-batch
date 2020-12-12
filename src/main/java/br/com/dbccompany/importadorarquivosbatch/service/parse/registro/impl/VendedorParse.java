package br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Vendedor;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.builder.VendedorBuilder;
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
        return VendedorBuilder.umVendedor()
                .comId(registro[0])
                .comCpf(registro[1])
                .comNome(registro[2])
                .comSalario(parseDouble(registro[3]))
                .build();
    }

    @Override
    protected String obterNome() {
        return Vendedor.class.getSimpleName();
    }
}