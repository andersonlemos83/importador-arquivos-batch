package br.com.dbccompany.importadorarquivosbatch.repository.parse.registro;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;

public interface RegistroParse {

    Registro parse(String[] registro);

}