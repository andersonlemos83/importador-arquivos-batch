package br.com.dbccompany.importadorarquivosbatch.repository.parse.factory;

import br.com.dbccompany.importadorarquivosbatch.repository.parse.registro.RegistroParse;

public interface RegistroParseFactory {

    RegistroParse obter(String[] registro);

}