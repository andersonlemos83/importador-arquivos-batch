package br.com.dbccompany.importadorarquivosbatch.domain.registro.builder;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Cliente;

public final class ClienteBuilder {

    private Cliente cliente = new Cliente();

    public static ClienteBuilder umCliente() {
        return new ClienteBuilder();
    }

    public ClienteBuilder comId(String id) {
        cliente.setId(id);
        return this;
    }

    public ClienteBuilder comCnpj(String cnpj) {
        cliente.setCnpj(cnpj);
        return this;
    }

    public ClienteBuilder comNome(String nome) {
        cliente.setNome(nome);
        return this;
    }

    public ClienteBuilder comAreaNegocio(String areaNegocio) {
        cliente.setAreaNegocio(areaNegocio);
        return this;
    }

    public Cliente build() {
        return cliente;
    }
}
