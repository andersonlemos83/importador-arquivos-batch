package br.com.dbccompany.importadorarquivosbatch.repository.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Vendedor;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.builder.VendedorBuilder;
import br.com.dbccompany.importadorarquivosbatch.repository.parse.registro.RegistroParse;
import org.springframework.stereotype.Component;

import static java.lang.Double.parseDouble;

@Component
public class VendedorParse extends AbstractRegistroParse implements RegistroParse {

    @Override
    protected int obterQuantidadeCampos() {
        return 4;
    }

    @Override
    protected Registro gerarRegistro(String[] registro) {
        return VendedorBuilder.umVendedor()
                .comId(registro[0])
                .comCpf(registro[1])
                .comNome(registro[2])
                .comSalario(parseDouble(registro[3]))
                .build();
    }

    @Override
    protected String obterNome() {
        return Vendedor.class.getName();
    }
}