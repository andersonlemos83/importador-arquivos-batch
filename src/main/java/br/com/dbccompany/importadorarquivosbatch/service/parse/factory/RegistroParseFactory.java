package br.com.dbccompany.importadorarquivosbatch.service.parse.factory;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;

public interface RegistroParseFactory<T extends Registro> {

    RegistroParse<T> obter(String[] registro);

}