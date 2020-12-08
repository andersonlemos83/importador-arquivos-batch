package br.com.dbccompany.importadorarquivosbatch.service.consolidador;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;

import java.util.List;

public interface ConsolidadorPiorVendedor {

    String consolidar(List<Registro> registros);

}