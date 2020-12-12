package br.com.dbccompany.importadorarquivosbatch.service.parse;

import br.com.dbccompany.importadorarquivosbatch.domain.Arquivo;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;

import java.util.List;

public interface ArquivoParse {

    List<Registro> parse(Arquivo arquivo);

}