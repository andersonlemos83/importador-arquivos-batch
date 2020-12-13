package br.com.dbccompany.importadorarquivosbatch.domain.registro.builder;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Vendedor;

public final class VendedorBuilder {

    private final Vendedor vendedor = new Vendedor();

    public static VendedorBuilder umVendedor() {
        return new VendedorBuilder();
    }

    public VendedorBuilder comId(String id) {
        vendedor.setId(id);
        return this;
    }

    public VendedorBuilder comCpf(String cpf) {
        vendedor.setCpf(cpf);
        return this;
    }

    public VendedorBuilder comNome(String nome) {
        vendedor.setNome(nome);
        return this;
    }

    public VendedorBuilder comSalario(Double salario) {
        vendedor.setSalario(salario);
        return this;
    }

    public Vendedor build() {
        return vendedor;
    }
}
