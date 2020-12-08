package br.com.dbccompany.importadorarquivosbatch.domain.registro;

import static br.com.dbccompany.importadorarquivosbatch.domain.registro.TipoRegistro.CLIENTE;
import static java.lang.Boolean.FALSE;

public class Cliente implements Registro {

    private String id;
    private String cnpj;
    private String nome;
    private String areaNegocio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAreaNegocio() {
        return areaNegocio;
    }

    public void setAreaNegocio(String areaNegocio) {
        this.areaNegocio = areaNegocio;
    }

    @Override
    public Boolean ehCliente() {
        return CLIENTE.getId().equalsIgnoreCase(id);
    }

    @Override
    public Boolean ehVendedor() {
        return FALSE;
    }

    @Override
    public Boolean ehVenda() {
        return FALSE;
    }

    @Override
    public String toString() {
        return "DadosCliente{" +
                "id='" + id + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                ", areaNegocio='" + areaNegocio + '\'' +
                '}';
    }
}