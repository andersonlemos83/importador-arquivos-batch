package br.com.dbccompany.importadorarquivosbatch.domain.registro;

import static java.lang.Boolean.FALSE;

public interface Registro {

    default Boolean ehCliente() {
        return FALSE;
    }

    default Boolean ehVendedor() {
        return FALSE;
    }

    default Boolean ehVenda() {
        return FALSE;
    }

}