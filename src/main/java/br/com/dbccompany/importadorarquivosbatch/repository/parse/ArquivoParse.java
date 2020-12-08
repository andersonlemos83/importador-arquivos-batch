package br.com.dbccompany.importadorarquivosbatch.repository.parse;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;

import java.util.List;

public interface ArquivoParse {

    List<Registro> parse(List<String[]> registros);

}