package br.com.dbccompany.importadorarquivosbatch.domain.registro;

public enum TipoRegistro {

    VENDEDOR("001"),
    CLIENTE("002"),
    VENDA("003");

    private final String id;

    TipoRegistro(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}