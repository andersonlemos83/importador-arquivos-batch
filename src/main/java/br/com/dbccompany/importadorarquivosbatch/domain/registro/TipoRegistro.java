package br.com.dbccompany.importadorarquivosbatch.domain.registro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum TipoRegistro {

    VENDEDOR("001"),
    CLIENTE("002"),
    VENDA("003");

    private final String id;

}