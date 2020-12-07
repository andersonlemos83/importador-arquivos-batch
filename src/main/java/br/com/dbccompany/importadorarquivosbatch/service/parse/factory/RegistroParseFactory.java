package br.com.dbccompany.importadorarquivosbatch.service.parse.factory;

import br.com.dbccompany.importadorarquivosbatch.service.parse.RegistroParse;

public interface RegistroParseFactory {

    RegistroParse obter(String id);

}