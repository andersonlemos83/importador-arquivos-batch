package br.com.dbccompany.importadorarquivosbatch.service;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;

import java.util.List;

public interface ConsolidadorQuantidadeVendedores {

    Long consolidar(List<Registro> registros);

}