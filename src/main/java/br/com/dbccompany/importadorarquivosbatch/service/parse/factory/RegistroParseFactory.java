package br.com.dbccompany.importadorarquivosbatch.service.parse.factory;

import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;

public interface RegistroParseFactory {

    RegistroParse obter(String[] registro);

}