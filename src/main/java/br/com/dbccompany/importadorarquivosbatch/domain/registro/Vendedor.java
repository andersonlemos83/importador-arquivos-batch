package br.com.dbccompany.importadorarquivosbatch.domain.registro;

import static br.com.dbccompany.importadorarquivosbatch.domain.registro.TipoRegistro.VENDEDOR;
import static java.lang.Boolean.FALSE;

public class Vendedor implements Registro {

    private String id;
    private String cpf;
    private String nome;
    private Double salario;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Override
    public Boolean ehCliente() {
        return FALSE;
    }

    @Override
    public Boolean ehVendedor() {
        return VENDEDOR.getId().equalsIgnoreCase(id);
    }

    @Override
    public Boolean ehVenda() {
        return FALSE;
    }

    @Override
    public String toString() {
        return "DadosVendedor{" +
                "id='" + id + '\'' +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", salario=" + salario +
                '}';
    }
}