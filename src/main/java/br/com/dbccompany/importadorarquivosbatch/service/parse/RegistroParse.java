package br.com.dbccompany.importadorarquivosbatch.service.parse;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;

public interface RegistroParse {

    Registro parse(String[] registro);

}